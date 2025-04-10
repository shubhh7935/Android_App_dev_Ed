package com.example.androidapplicationdevelopment.UNITS

import android.Manifest
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapplicationdevelopment.MainActivity
import com.example.androidapplicationdevelopment.MyAlarmReceiver
import com.example.androidapplicationdevelopment.R

class Communicationandscheduling : AppCompatActivity() {

    lateinit var btnExplicit: Button
    lateinit var btnImplicit: Button
    lateinit var btnAlarm: Button
    lateinit var btnNotify: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicationandscheduling)

        btnExplicit = findViewById(R.id.btnExplicitIntent)
        btnImplicit = findViewById(R.id.btnImplicitIntent)
        btnAlarm = findViewById(R.id.btnAlarm)
        btnNotify = findViewById(R.id.btnNotify)

        // Explicit Intent
        btnExplicit.setOnClickListener {
            val intent = Intent(this, Viewsandcomponents::class.java)
            startActivity(intent)
        }

        // Implicit Intent (Open Browser)
        btnImplicit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }

        // Alarm Manager with Pending Intent
        btnAlarm.setOnClickListener {
            val intent = Intent(this, MyAlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val time = System.currentTimeMillis() + 5000 // 5 seconds later

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        }

        // Notification
        btnNotify.setOnClickListener {
            showNotification()
        }

        createNotificationChannel() // for Android 8+
        //Starting from Android 13 (API 33), apps must ask users at runtime for the POST_NOTIFICATIONS
        // permission to show notifications. Without it, even if your app sends a notification,
        // it won't appear.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = Notification.Builder(this, "channel_id")
            .setContentTitle("Android Dev Notification")
            .setContentText("This is a sample notification.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ChannelName"
            val desc = "Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channel_id", name, importance).apply {
                description = desc
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
