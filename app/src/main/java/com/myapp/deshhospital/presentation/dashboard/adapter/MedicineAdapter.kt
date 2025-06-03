package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Medicine

class MedicineAdapter(private var data:ArrayList<Medicine>, internal var context: Context):RecyclerView.Adapter<MedicineAdapter.ViewHolder>(){
    var onItemClick:((Medicine)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.medicineItemName)
        val company:TextView = itemView.findViewById(R.id.medicineItemCompany)
        val image:ImageView = itemView.findViewById(R.id.medicineItemImage)
        val card:CardView = itemView.findViewById(R.id.medicineListItemCard)
        fun bind(medicine: Medicine){
            name.text = medicine.name
            company.text = medicine.company
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.medicine_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        Glide.with(context).load(item.image).into(holder.image)
        holder.card.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}