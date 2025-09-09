package io.github.saifulislamniloy

import android.R
import android.app.Service
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.io.use
import kotlin.random.Random
import kotlin.ranges.coerceAtMost

class RandomSenderService : Service() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val client by lazy {
        OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            // .addInterceptor(HttpLoggingInterceptor().apply { level = BODY }) // debug only
            .build()
    }
    private val json = Json { ignoreUnknownKeys = true }

    override fun onCreate() {
        super.onCreate()
        startForeground(
            1001,
            NotificationCompat.Builder(this, "sender")
                .setSmallIcon(R.drawable.stat_sys_upload)
                .setContentTitle("Sending every 10s")
                .setContentText("Active")
                .setOngoing(true)
                .build()
        )

        scope.launch {
            var backoff = 1_000L // start with 1s
            while (isActive) {
                val payload = Payload(
                    userId = UUID.randomUUID().toString(),
                    data = Random.nextInt(0, 1_000_000).toString(),
                    clientTimestamp = generateInstantTimeStampToString()
                )
                val ok = send(payload)
                backoff = if (ok) 1_000L else (backoff * 2).coerceAtMost(3_000L)
                delay(if (ok) 10_000L else backoff) // retry sooner with backoff when failing
            }
        }
    }

    private fun generateInstantTimeStampToString(): String {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val sdf = SimpleDateFormat(pattern, Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return sdf.format(Date(System.currentTimeMillis()))
    }

    @Serializable
    data class Payload(val userId: String, val data: String, val clientTimestamp: String)

    fun Context.readBaseUrlFromAssets(): String {
        return assets.open("config.properties").use { s ->
            java.util.Properties().apply { load(s) }.getProperty("base_url")
        }
    }

    private suspend fun send(p: Payload): Boolean = withContext(Dispatchers.IO) {
        val base = readBaseUrlFromAssets().trimEnd('/')
        val url = "$base/api/v1/ingestion"

        val bodyJson = json.encodeToString(Payload.serializer(), p)
        val req = Request.Builder()
            .url(url)
            .addHeader("accept", "*/*")
            .post(bodyJson.toRequestBody("application/json".toMediaType()))
            .build()

        try {
            client.newCall(req).execute().use { it.isSuccessful }
        } catch (t: Throwable) {
            Log.e("NET", "Request failed", t)
            false
        }
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?) = null
}
