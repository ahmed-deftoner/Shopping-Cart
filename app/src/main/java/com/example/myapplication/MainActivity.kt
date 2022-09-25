package com.example.myapplication

import Models.CourseModel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseAdapter
    lateinit var templist : ArrayList<CourseModel>
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

        templist = ArrayList()
        templist.addAll(courseModelArrayList)
        // we are initializing our adapter class and passing our arraylist to it.
        val courseAdapter = CourseAdapter(this, templist
        ) { position -> onListItemClick(position) }

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.layoutManager = linearLayoutManager
        courseRV.adapter = courseAdapter

    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_items, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem? = menu.findItem(R.id.search_action)

        // getting search view of our item.
        val textView: EditText = searchItem?.actionView as EditText

        textView.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                templist.clear()
                val searchText = textView.text.toString().lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    courseModelArrayList.forEach {
                        if (it.getCourse_name().lowercase(Locale.getDefault()).contains(searchText))
                            templist.add(it)
                    }
                    courseRVAdapter.notifyDataSetChanged()
                }else{
                    templist.clear()
                    templist.addAll(courseModelArrayList)
                    courseRVAdapter.notifyDataSetChanged()
                }
            }


            override fun afterTextChanged(p0: Editable?) {

            }

        })


        return true
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(this, Details::class.java).apply {
            putExtra(EXTRA_MESSAGE, templist[position].getCourse_name())
        }
        startActivity(intent)
        //Toast.makeText(this, templist[position].getCourse_name(), Toast.LENGTH_SHORT).show()
    }
}
