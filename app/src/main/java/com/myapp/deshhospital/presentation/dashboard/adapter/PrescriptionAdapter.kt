package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Doctor
import com.myapp.deshhospital.presentation.model.Prescription

class PrescriptionAdapter(private var data:ArrayList<Prescription>,internal var context: Context):RecyclerView.Adapter<PrescriptionAdapter.ViewHolder>(){
    var onItemClick:((Prescription)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val date:TextView = itemView.findViewById(R.id.dateTv)
        val doctorName:TextView = itemView.findViewById(R.id.doctorNameTv)
        val card:CardView = itemView.findViewById(R.id.prescriptioCard)
        fun bind(prescription: Prescription){
            date.text = prescription.date
            doctorName.text = prescription.docName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.prescription_item,parent,false)
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