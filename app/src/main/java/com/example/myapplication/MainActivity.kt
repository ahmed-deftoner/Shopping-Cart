package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Models.CourseModel
import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseAdapter
    lateinit var courseModelArrayList: ArrayList<CourseModel>


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseRV = findViewById(R.id.idRVCourses)

        // Here, we have created new array list and added data to it
        courseModelArrayList = ArrayList()
        courseModelArrayList.add(CourseModel("DSA in Java", 4, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("Java Course", 3, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("C++ Course", 4, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("DSA in C++", 4, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("Kotlin for Android", 4, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("Java for Android", 4, R.drawable.gfg))
        courseModelArrayList.add(CourseModel("HTML and CSS", 4, R.drawable.gfg))

        // we are initializing our adapter class and passing our arraylist to it.
        val courseAdapter = CourseAdapter(this, courseModelArrayList)

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.layoutManager = linearLayoutManager
        courseRV.adapter = courseAdapter
        courseAdapter.notifyDataSetChanged()
    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView


        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<CourseModel> = ArrayList()

        // running a for loop to compare elements.
        for (item in courseModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourse_name().lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            courseRVAdapter.filterList(filteredlist)
        }
    }
}
