package com.example.myapplication

import Models.Guitars
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView

class Details : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val intent = getIntent()
        val guitar: Guitars? = intent.getSerializableExtra("guitar") as Guitars?
        val name: TextView = findViewById(R.id.name);
        val rating: RatingBar = findViewById(R.id.rating)
        val description: TextView = findViewById(R.id.description)
        val body: TextView = findViewById(R.id.body)
        val scale: TextView = findViewById(R.id.scale)
        val price: TextView = findViewById(R.id.price)
        if (guitar != null) {
            name.setText(guitar.name)
            description.setText(guitar.description)
            body.setText(guitar.body)
            scale.setText(guitar.scaleLength.toString())
            price.setText(guitar.price.toString())
            rating.rating = guitar.rating.toFloat()
        }
    }
}