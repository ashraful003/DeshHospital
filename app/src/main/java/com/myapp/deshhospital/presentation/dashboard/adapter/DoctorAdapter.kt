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
import com.myapp.deshhospital.presentation.model.Doctor

class DoctorAdapter(private var data:ArrayList<Doctor>,internal var context: Context):RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {
    var onItemClick:((Doctor)->Unit)? = null
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val name:TextView = itemView.findViewById(R.id.doctorItemName)
      val post:TextView = itemView.findViewById(R.id.doctorItemPost)
      val qualification:TextView = itemView.findViewById(R.id.doctorItemQualification)
      val imageV:ImageView = itemView.findViewById(R.id.doctorItemImage)
      val card:CardView = itemView.findViewById(R.id.doctorItemCard)
        fun bind(doctor: Doctor){
            name.text = doctor.name
            post.text = doctor.post
            qualification.text = doctor.qualification
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(context).inflate(R.layout.doctor_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.imageV.setImageResource(item?.image!!)
        holder.card.setOnClickListener {
           onItemClick?.invoke(item)
        }
    }
    fun searchDataList(searchListData: List<Doctor>) {
        data = ArrayList(searchListData)
        notifyDataSetChanged()
    }
}