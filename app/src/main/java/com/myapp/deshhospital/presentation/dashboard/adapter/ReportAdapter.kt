package com.myapp.deshhospital.presentation.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.deshhospital.R
import com.myapp.deshhospital.presentation.model.Report

class ReportAdapter(private var data:ArrayList<Report>, internal var context: Context):RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    var onItemClick:((Report)->Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val date:TextView = itemView.findViewById(R.id.reportDateTv)
        val diagnosName:TextView = itemView.findViewById(R.id.diagnosticNameTv)
        val card:CardView = itemView.findViewById(R.id.reportCard)
        fun bind(report: Report){
            date.text = report.date
            diagnosName.text = report.diagnosticName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.report_item,parent,false)
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