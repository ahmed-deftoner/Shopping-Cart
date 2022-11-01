package com.example.myapplication

import DB.DBHelper
import Models.Guitars
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import java.io.ByteArrayOutputStream

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
            val btn = findViewById<Button>(R.id.addcartbutton)

            btn.setOnClickListener {
                val db = DBHelper(this, null)
                val bitmap = BitmapFactory.decodeResource(resources, guitar.imgSrc)
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                val img: ByteArray = bos.toByteArray()
                db.addtoCart(guitar.name, guitar.price.toString(),
                    img, guitar.description, guitar.body, guitar.scaleLength.toString(),
                    guitar.rating.toString())
                Toast.makeText(this@Details, "Added to cart.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}