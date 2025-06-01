package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Ambulance

class AmbulanceAdapter(private var data:ArrayList<Ambulance>, internal var context: Context):RecyclerView.Adapter<AmbulanceAdapter.ViewHolder>(){
    var onItemClick:((Ambulance)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val area:TextView = itemView.findViewById(R.id.areaNameTv)
        val card:CardView = itemView.findViewById(R.id.ambulanceCard)

        fun bind(ambulance: Ambulance){
            area.text = ambulance.area
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.ambulance_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.card.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}