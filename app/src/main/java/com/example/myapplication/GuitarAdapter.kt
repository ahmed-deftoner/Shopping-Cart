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

class GuitarAdapter(private val context: Context, guitarModelArrayList: ArrayList<Guitars>,
                    private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<GuitarAdapter.ViewHolder>() {
    private var guitarModelArrayList: ArrayList<Guitars>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuitarAdapter.ViewHolder {
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
        val guitarIV: ImageView
        val guitarNameTV: TextView
        val guitarRatingTV: TextView
        init {
            itemView.setOnClickListener(this)
            guitarIV = itemView.findViewById(R.id.idIVGuitarImage)
            guitarNameTV = itemView.findViewById(R.id.idTVGuitarName)
            guitarRatingTV = itemView.findViewById(R.id.idTVGuitarRating)
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
        holder.guitarNameTV.setText(model.name)
        holder.guitarRatingTV.setText("" + model.rating)
        holder.guitarIV.setImageResource(model.imgSrc)
    }
}
