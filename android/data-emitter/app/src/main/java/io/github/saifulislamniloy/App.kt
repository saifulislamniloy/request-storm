package io.github.saifulislamniloy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import kotlin.apply
import kotlin.jvm.java

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "sender",
                "10s Sender",
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Sends random numbers every 10 seconds" }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }
}
