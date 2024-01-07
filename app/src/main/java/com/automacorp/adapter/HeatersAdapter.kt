package com.automacorp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.R
import com.automacorp.model.HeaterDto

class HeatersAdapter : RecyclerView.Adapter<HeatersAdapter.HeatersViewHolder>() {

    inner class HeatersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_window_name_room)
        val currentStatus: TextView = view.findViewById(R.id.txt_window_status)
    }

    private val items = mutableListOf<HeaterDto>()

    fun setItems(heaters: List<HeaterDto>) {
        items.clear()
        items.addAll(heaters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeatersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_windows_item, parent, false)
        return HeatersViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeatersViewHolder, position: Int) {  // (7)
        val heaterDto = items[position]
        holder.apply {
            name.text = heaterDto.name
            currentStatus.text = heaterDto.heaterStatus.toString()
        }
    }
}