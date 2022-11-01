package com.example.myapplication

import DB.DBHelper
import Models.Guitars
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class Cart : AppCompatActivity() {


    lateinit var guitarRV: RecyclerView
    lateinit var guitarModelArrayList: ArrayList<Guitars>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        guitarRV = findViewById(R.id.cartGuitars)
        val db = DBHelper(this, null)
        db.getCart()
    }

}