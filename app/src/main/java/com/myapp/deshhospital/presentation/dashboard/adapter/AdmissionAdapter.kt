package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Admission

class AdmissionAdapter(private var data:ArrayList<Admission>,internal var context: Context):RecyclerView.Adapter<AdmissionAdapter.ViewHolder>() {
    var onItemClick:((Admission)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val date:TextView = itemView.findViewById(R.id.admissionDateTv)
        val identity:TextView= itemView.findViewById(R.id.addPatientIdentityTv)
        val card:CardView = itemView.findViewById(R.id.admissionCard)
        fun bind(admission: Admission){
            date.text = admission.date
            identity.text = admission.addIdentity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(context).inflate(R.layout.admission_patient_item,parent,false)
        return ViewHolder(viewItem)
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