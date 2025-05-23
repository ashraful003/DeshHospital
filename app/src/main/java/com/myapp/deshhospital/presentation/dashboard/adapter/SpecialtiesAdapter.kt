package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Specialties

class SpecialtiesAdapter(private var data:ArrayList<Specialties>, var context: Context):RecyclerView.Adapter<SpecialtiesAdapter.ViewHolder>() {
    var ItemClick:((Specialties)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.specialtiesItemName)
        val imageV:ImageView = itemView.findViewById(R.id.specialtiesItemImage)
        val card:CardView = itemView.findViewById(R.id.specialtiesItemCard)
        fun bind(specialties: Specialties){
            name.text = specialties.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.specialties_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        item.image?.let { holder.imageV.setImageResource(it) }
        holder.card.setOnClickListener {
            ItemClick?.invoke(item)
        }
    }
}