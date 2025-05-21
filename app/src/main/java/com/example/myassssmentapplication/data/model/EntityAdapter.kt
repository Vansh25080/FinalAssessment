package com.example.myassssmentapplication.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.R

class EntityAdapter(
    private var entityList: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProp1: TextView = itemView.findViewById(R.id.tvProp1)
        val tvProp2: TextView = itemView.findViewById(R.id.tvProp2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entityList[position]
        // Display assetType and ticker values
        holder.tvProp1.text = entity.assetType ?: "N/A"
        holder.tvProp2.text = entity.ticker ?: "N/A"
        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    override fun getItemCount(): Int = entityList.size

    fun updateData(newEntities: List<Entity>) {
        entityList = newEntities
        notifyDataSetChanged()
    }
}
