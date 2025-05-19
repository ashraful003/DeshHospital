package com.myapp.deshhospital.presentation.dashboard.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.myapp.medicinestore.R

class ViewPageAdapter(private val views:List<View>):RecyclerView.Adapter<ViewPageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val container:FrameLayout = itemView.findViewById(R.id.view_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loding_landing_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return views.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.container.addView(views[position])
    }
}