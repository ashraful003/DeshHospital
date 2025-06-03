package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Pharmaceutical

class PharmaceuticalAdapter(private var data:ArrayList<Pharmaceutical>, internal var context: Context):RecyclerView.Adapter<PharmaceuticalAdapter.ViewHolder>(){
    var onItemClick:((Pharmaceutical)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val pharmaName:TextView = itemView.findViewById(R.id.pharmaceuticalNameTv)
        val card:CardView = itemView.findViewById(R.id.pharmaceuticalCard)
        fun bind(pharmaceutical: Pharmaceutical){
            pharmaName.text = pharmaceutical.pharmaName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pharmaceutical_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.bind(item)
        holder.card.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}