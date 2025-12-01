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
import com.myapp.deshhospital.presentation.model.Employee

class EmployeeAdapter(private var data:ArrayList<Employee>, internal var context: Context):RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){
    var onItemClick:((Employee) -> Unit)? = null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.employeeListItemName)
        val post:TextView = itemView.findViewById(R.id.employeeListItemPost)
        val image:ImageView = itemView.findViewById(R.id.employeeListItemImage)
        val card:CardView = itemView.findViewById(R.id.employeeListItemCard)

        fun bind(employee: Employee){
            name.text = employee.name
            post.text = employee.post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  item = data[position]
        holder.bind(item)
        Glide.with(context).load(item.image).into(holder.image)
        holder.card.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}