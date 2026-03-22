package com.powerfill.app

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val sessions: List<Session>) :
    RecyclerView.Adapter<HistoryAdapter.Holder>() {

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val energy: TextView = item.findViewById(android.R.id.text1)
        val cost: TextView = item.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val s = sessions[position]
        holder.energy.text = "Energy: %.2f kWh".format(s.energy)
        holder.cost.text = "Cost: %.2f €".format(s.cost ?: 0.0)
    }

    override fun getItemCount(): Int = sessions.size
}
