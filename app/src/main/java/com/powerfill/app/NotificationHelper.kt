package com.powerfill.app
import android.app.*
import android.content.Context
import androidx.core.app.NotificationCompat

class NotificationHelper(context: Context) {
    private val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    init {
        val channel = NotificationChannel("charging","Charging", NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
    }
    fun show(context: Context, title: String, msg: String) {
        val n = NotificationCompat.Builder(context,"charging")
            .setContentTitle(title)
            .setContentText(msg)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        manager.notify(System.currentTimeMillis().toInt(), n)
    }
}
