package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        val button = findViewById<Button>(R.id.dialerbtn)
        button.setOnClickListener(View.OnClickListener { openDialer() })
    }

    fun openDialer() {
        val number = "0305-4183689"
        val uri = "tel:" + number.trim { it <= ' ' }
        val intent2 = Intent(Intent.ACTION_DIAL)
        intent2.data = Uri.parse(uri)
        startActivity(intent2)
    }
}