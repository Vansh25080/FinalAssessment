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
        holder.tvProp1.text = entity.property1 ?: "N/A"
        holder.tvProp2.text = entity.property2 ?: "N/A"
        // Log debug information to check data binding.
        android.util.Log.d(
            "EntityAdapter",
            "Binding entity at position $position with property1: ${entity.property1} and property2: ${entity.property2}"
        )
        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    override fun getItemCount(): Int = entityList.size

    // Update adapter's dataset and refresh the list
    fun updateData(newEntities: List<Entity>) {
        android.util.Log.d("EntityAdapter", "Updating data with ${newEntities.size} entities")
        entityList = newEntities
        notifyDataSetChanged()
    }
}
