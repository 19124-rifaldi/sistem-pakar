package com.example.sistempakarikannila.ui.select_symptom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempakarikannila.R
import com.google.android.material.chip.Chip

class GejalaAdapter(
    private val data : List<String>
):RecyclerView.Adapter<GejalaAdapter.GejalaViewHolder>() {
    inner class GejalaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val chip: Chip = itemView.findViewById(R.id.chipgejala)
    }
    private val selectedItems: ArrayList<String> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GejalaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.symptomp_list,parent,false)
        return GejalaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GejalaViewHolder, position: Int) {
        val gejala = data[position]
        holder.chip.text = gejala

        val colorChange = if(gejala in selectedItems) R.color.blue_primary else R.color.blue_secondary
        holder.chip.setChipBackgroundColorResource(colorChange)

        holder.chip.setOnClickListener{
            if (gejala in selectedItems){
                selectedItems.remove(gejala)
            }else {
                selectedItems.add(gejala)
            }
            notifyDataSetChanged()

        }

        holder.chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                selectedItems.add(gejala)
            }else{
                selectedItems.remove(gejala)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun getSelectedItems():ArrayList<String>{
        return selectedItems
    }
}