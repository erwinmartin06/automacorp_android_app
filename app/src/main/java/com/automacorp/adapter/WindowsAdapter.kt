package com.automacorp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.R
import com.automacorp.model.WindowDto

class WindowsAdapter : RecyclerView.Adapter<WindowsAdapter.WindowsViewHolder>() {

    inner class WindowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_window_name_room)
        val currentStatus: TextView = view.findViewById(R.id.txt_window_status)
    }

    private val items = mutableListOf<WindowDto>()

    fun setItems(windows: List<WindowDto>) {
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_windows_item, parent, false)
        return WindowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowsViewHolder, position: Int) {  // (7)
        val windowDto = items[position]
        holder.apply {
            name.text = windowDto.name
            currentStatus.text = windowDto.windowStatus.toString()
        }
    }
}