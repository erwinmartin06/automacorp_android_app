package com.automacorp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.OnBuildingClickListener
import com.automacorp.R
import com.automacorp.model.BuildingDto

class BuildingsAdapter(val listener: OnBuildingClickListener) : RecyclerView.Adapter<BuildingsAdapter.BuildingsViewHolder>() {

    inner class BuildingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_room_name2)
        val outsideTemperature: TextView = view.findViewById(R.id.txt_current_temperature_value)
    }

    private val items = mutableListOf<BuildingDto>()

    fun setItems(buildings: List<BuildingDto>) {
        items.clear()
        items.addAll(buildings)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_buildings_item, parent, false)
        return BuildingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingsViewHolder, position: Int) {
        val buildingDto = items[position]
        holder.apply {
            name.text = buildingDto.name
            outsideTemperature.text = buildingDto.outsideTemperature.toString()
            itemView.setOnClickListener { listener.selectBuilding(buildingDto.name) }
        }
    }

    override fun onViewRecycled(holder: BuildingsAdapter.BuildingsViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }
}