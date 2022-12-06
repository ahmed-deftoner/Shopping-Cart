package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    var btnAddtoCart: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        val name = "Stinger"
        val price = 2300
        val image = R.drawable.gfg
        val id = 34
        val desc_id = 56
        val description: String = java.lang.String.valueOf(R.string.descriptionstring) + desc_id

        //itemsDeclaration();
        val nameTextView = findViewById<TextView>(R.id.product_name)
        nameTextView.text = name
        val priceTag = findViewById<TextView>(R.id.priceTag)
        priceTag.text = price.toString()
        val img: ImageView = findViewById(R.id.imageView2)
        img.setImageResource(image)
        btnAddtoCart = findViewById(R.id.buttoncart)

    }
}
