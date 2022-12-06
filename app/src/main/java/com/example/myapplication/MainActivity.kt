package com.example.myapplication


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "23"
    val CHANNEL_NAME = "cartNotify"
    val NOTIF_ID = 0

    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotifChannel()
        val name = "Stinger"
        val price = 2300
        val image = R.drawable.gfg
        //val desc_id = 56
        //val description: String = java.lang.String.valueOf(R.string.descriptionstring) + desc_id

        //itemsDeclaration();
        val nameTextView = findViewById<TextView>(R.id.product_name)
        nameTextView.text = name
        val priceTag = findViewById<TextView>(R.id.priceTag)
        priceTag.text = "$$price"
        val img: ImageView = findViewById(R.id.imageView2)
        img.setImageResource(image)
        val btnAddCart = findViewById<Button>(R.id.buttoncart)

        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notif = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("You bought $name")
            .setContentText("Just for $price . Enjoy ya cheeky bastard!")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Just for $price . Enjoy ya cheeky bastard!\nSale in the next week... Catch up"))
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(getResources(), image))
                .bigLargeIcon(null))
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notifManger = NotificationManagerCompat.from(this)


        // onClick listener for the button
        btnAddCart.setOnClickListener {
            notifManger.notify(NOTIF_ID,notif)

        }
    }
}
