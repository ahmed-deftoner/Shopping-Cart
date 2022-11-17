package com.example.myapplication
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Models.Guitars
import android.annotation.SuppressLint
import android.widget.RatingBar

class CartAdapter(private val context: Context, guitarModelArrayList: ArrayList<Guitars>,
                    private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var guitarModelArrayList: ArrayList<Guitars>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        // to inflate the layout for each item of recycler view.
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view,onItemClicked)
    }


    override fun getItemCount(): Int {
        // this method is used for showing number of card items in recycler view.
        return guitarModelArrayList.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    class ViewHolder(itemView: View,private val onItemClicked: (position: Int) -> Unit)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cartIV: ImageView
        val cartNameTV: TextView
        //val guitarRatingTV: RatingBar
        init {
            itemView.setOnClickListener(this)
            cartIV = itemView.findViewById(R.id.cartImg)
            cartNameTV = itemView.findViewById(R.id.cartName)
            //  guitarRatingTV = itemView.findViewById(R.id.rating)
        }
        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    // Constructor
    init {
        this.guitarModelArrayList = guitarModelArrayList
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Guitars = guitarModelArrayList[position]
        holder.cartNameTV.setText(model.name)
        //holder.guitarRatingTV.rating = model.rating.toFloat()
        holder.cartIV.setImageResource(model.imgSrc)
    }
}
