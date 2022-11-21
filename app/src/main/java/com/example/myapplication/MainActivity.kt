package com.example.myapplication

import Models.Guitars
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    lateinit var guitarRV: RecyclerView
    lateinit var guitars: ArrayList<Guitars>
    val ref = FirebaseDatabase.getInstance().getReference("guitars")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setContentView(R.layout.activity_main)
        guitarRV = findViewById(R.id.idRVGuitars)

        val guitarAdapter = GuitarAdapter(this, guitars)
        { position -> onListItemClick(position) }
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        guitarRV.layoutManager = linearLayoutManager
        guitarRV.adapter = guitarAdapter

        // Write a message to the database
        /*val database = Firebase.database
        val myRef = database.getReference("guitars")

        myRef.setValue(guitarModelArrayList)*/

    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_items, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.cart_menu) {
            val intent = Intent(this, Cart::class.java).apply {}
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onListItemClick(position: Int) {
        Toast.makeText(this, guitars[position].name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Details::class.java).apply {
            putExtra("guitar", guitars[position] as java.io.Serializable)
        }
        startActivity(intent)
        //Toast.makeText(this, templist[position].getCourse_name(), Toast.LENGTH_SHORT).show()
    }
}
