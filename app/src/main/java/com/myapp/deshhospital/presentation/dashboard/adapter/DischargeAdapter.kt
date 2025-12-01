package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Discharge

class DischargeAdapter(private var data:ArrayList<Discharge>, internal var context: Context):RecyclerView.Adapter<DischargeAdapter.ViewHolder>(){
    var onItemClick:((Discharge) -> Unit)? =null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var date:TextView = itemView.findViewById(R.id.disDateTv)
        var docName:TextView = itemView.findViewById(R.id.dicDoctorNameTv)
        var card:CardView = itemView.findViewById(R.id.dischargeCard)
        fun bind(discharge: Discharge){
            date.text = discharge.date
            docName.text = discharge.docName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.discharge_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.card.setOnClickListener {
            onItemClick!!.invoke(item)
        }
    }
}