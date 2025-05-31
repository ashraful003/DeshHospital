package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Blood

class BloodDonorAdapter(private var data:ArrayList<Blood>, internal var context: Context):RecyclerView.Adapter<BloodDonorAdapter.ViewHolder>(){
    var onClickItem:((Blood)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.bloodDonorNameTv)
        val area:TextView = itemView.findViewById(R.id.bloodDonorAreaTv)
        val card:CardView = itemView.findViewById(R.id.donorCard)
        fun bind(blood: Blood){
            name.text = blood.name
            area.text = blood.area
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.donor_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.card.setOnClickListener {
            onClickItem?.invoke(item)
        }
    }
}