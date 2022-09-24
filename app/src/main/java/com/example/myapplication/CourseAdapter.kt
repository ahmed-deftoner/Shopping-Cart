package com.example.myapplication
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Models.CourseModel
import android.annotation.SuppressLint

class CourseAdapter(private val context: Context, courseModelArrayList: ArrayList<CourseModel>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    private var courseModelArrayList: ArrayList<CourseModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        // to inflate the layout for each item of recycler view.
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<CourseModel>) {
        // below line is to add our filtered
        // list in our course array list.
        courseModelArrayList.clear()
        courseModelArrayList.addAll(filterlist)
        // below line is to notify our adapter
        // as change in recycler view data.
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CourseAdapter.ViewHolder, position: Int) {
        // to set data to textview and imageview of each card layout
        val model: CourseModel = courseModelArrayList[position]
        holder.courseNameTV.setText(model.getCourse_name())
        holder.courseRatingTV.setText("" + model.getCourse_rating())
        holder.courseIV.setImageResource(model.getCourse_image())
    }

    override fun getItemCount(): Int {
        // this method is used for showing number of card items in recycler view.
        return courseModelArrayList.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseIV: ImageView
        val courseNameTV: TextView
        val courseRatingTV: TextView
        init {
            courseIV = itemView.findViewById(R.id.idIVCourseImage)
            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseRatingTV = itemView.findViewById(R.id.idTVCourseRating)
        }
    }

    // Constructor
    init {
        this.courseModelArrayList = courseModelArrayList
    }
}
