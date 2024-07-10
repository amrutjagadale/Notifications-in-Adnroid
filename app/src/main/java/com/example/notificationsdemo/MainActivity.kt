package com.example.notificationsdemo

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationsdemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private var channelId = "bitcode_channel"
    val SIMPLE_NOTIFICATION = 101
    val BIG_PICTURE = 102
    val ACTION_TEXT_STYLE = 103
    val INBOXSTYLE_NITIFICATION = 104
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        notificationManager = NotificationManagerCompat.from(this)
        setContentView(binding.root)
        createNotificationChannel()
        bigPictureNotification()
        simpleNotification()
        actionTextStyleNotification()
        snackBarNotification()
        inboxTextStyleNotification()
    }
    @SuppressLint("MissingPermission")
    private fun simpleNotification(){
        binding.simpleNotification.setOnClickListener{
            var notificatinBuilder = NotificationCompat.Builder(this,channelId)
            notificatinBuilder.setSmallIcon(R.drawable.ic_launcher_background)
            notificatinBuilder.setContentTitle("Simple notification")
            notificatinBuilder.setContentText("This is simple notification")
            notificatinBuilder.setColor(Color.RED)
            notificatinBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            var actionIntent = Intent(this,SecondActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(this,
                1,
                actionIntent,
                PendingIntent.FLAG_MUTABLE)

            notificatinBuilder.setContentIntent(actionPendingIntent)
            var simpleNotification = notificatinBuilder.build()

            notificationManager.notify(SIMPLE_NOTIFICATION,simpleNotification)
        }
    }
    @SuppressLint("MissingPermission")
    private fun inboxTextStyleNotification(){

        binding.inboxNotification.setOnClickListener{
            var builder = NotificationCompat.Builder(this,channelId)
            builder.setContentText("Inbox style notification")
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            builder.setContentTitle("This is android april 2024")
            builder.setLights(Color.RED,100,100)

            var inboxStyle = NotificationCompat.InboxStyle(builder)
            inboxStyle.addLine("Android 2024")
            inboxStyle.addLine("ios 2024")
            inboxStyle.addLine("web 2024")
            inboxStyle.addLine("java 2024")

            builder.setStyle(inboxStyle)
            notificationManager.notify(INBOXSTYLE_NITIFICATION,builder.build())

        }
    }
    @SuppressLint("MissingPermission")
    private fun actionTextStyleNotification(){

        binding.actionTextStyleNotification.setOnClickListener{
            var builder = NotificationCompat.Builder(this,channelId)
            builder.setContentTitle("Action text style")
            builder.setContentText("Action text notification")
            builder.setSmallIcon(R.drawable.ic_launcher_background)

            var actionIntent = Intent(this,SecondActivity::class.java)

            var pendingIntent = PendingIntent.getActivity(this,
                1,
                actionIntent,
                PendingIntent.FLAG_IMMUTABLE)

            var actionTextStyle = NotificationCompat.Action(R.drawable.ic_launcher_background,
                "Action text style",
                pendingIntent)

            builder.addAction(actionTextStyle)
            builder.addAction(R.drawable.ic_launcher_background,"Action",pendingIntent)
            notificationManager.notify(ACTION_TEXT_STYLE,builder.build())
        }
    }
    @SuppressLint("MissingPermission")
    private fun bigPictureNotification(){
        binding.bigPictureNotification.setOnClickListener{
               var builder = NotificationCompat.Builder(this,channelId)
            builder.setContentTitle("Big picture style notification")
            builder.setContentText("This is bitcode big picture notification")
            builder.setColor(Color.MAGENTA)
            builder.setLights(Color.BLUE,100,100)
            builder.setSmallIcon(R.drawable.ic_launcher_background)

            var bigPictureStyle = NotificationCompat.BigPictureStyle(builder)
            bigPictureStyle.setBigContentTitle("Android april 2024")

            var bitmapImage = BitmapFactory.decodeResource(resources,R.drawable.image)
            bigPictureStyle.bigPicture(bitmapImage)
            builder.setStyle(bigPictureStyle)
notificationManager.notify(BIG_PICTURE,builder.build())
        }
    }
    private fun snackBarNotification(){
        binding.snakBarNotification.setOnClickListener {

            var snackBar =  Snackbar.make(binding.root,
                "SnackBar",
                Snackbar.LENGTH_LONG)

            snackBar.setTextColor(Color.MAGENTA)
            snackBar.setText("This is snack bar notification")
            snackBar.show()
        }
    }
    private fun createNotificationChannel(){
        var channelBitcodeBuilder = NotificationChannelCompat.Builder(channelId,NotificationManager.IMPORTANCE_MAX)
        channelBitcodeBuilder.setName("Bitcode channel")
        channelBitcodeBuilder.setDescription("This is Bitcode channel")
        channelBitcodeBuilder.setShowBadge(true)
        notificationManager.createNotificationChannel(channelBitcodeBuilder.build())
    }
}

