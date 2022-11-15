package com.example.myapplication

import Models.Guitars
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class Cart : AppCompatActivity() {


    lateinit var guitarRV: RecyclerView
    lateinit var guitars: ArrayList<Guitars>
    val ref = FirebaseDatabase.getInstance().getReference("cart")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        guitarRV = findViewById(R.id.cartGuitars)

        guitars = arrayListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    val guitar = productSnapshot.getValue(Guitars::class.java)
                    guitars.add(guitar!!)
                }
                println(guitars)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
        val guitarAdapter = GuitarAdapter(this, guitars)
        { position -> onListItemClick(position) }
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        guitarRV.layoutManager = linearLayoutManager
        guitarRV.adapter = guitarAdapter
    }

    private fun onListItemClick(position: Int) {
        Toast.makeText(this, guitars[position].name, Toast.LENGTH_SHORT).show()
    }
}