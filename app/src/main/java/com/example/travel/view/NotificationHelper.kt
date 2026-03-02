package com.example.travel.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.travel.model.NotificationModel
import com.google.firebase.database.FirebaseDatabase


class NotificationHelper(private val context: Context) {
    private val database = FirebaseDatabase.getInstance().getReference("notifications")

    fun sendBookingNotification(destination: String) {
        val notificationId = database.push().key ?: ""
        val notificationData = NotificationModel(
            id = notificationId,
            destination = destination,
            message = "Your booking for $destination has been successfully completed. Thank you!"
        )
        database.child(notificationId).setValue(notificationData)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "booking_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Booking Notifications", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setContentTitle("Booking Confirmed! 🎉")
            .setContentText("Your trip to $destination is ready.")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}