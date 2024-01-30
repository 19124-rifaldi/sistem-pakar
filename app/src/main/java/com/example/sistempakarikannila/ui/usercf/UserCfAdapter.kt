package com.example.sistempakarikannila.ui.usercf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempakarikannila.R
import com.example.sistempakarikannila.model.Gejala
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class UserCfAdapter(private val data : java.util.ArrayList<String>): RecyclerView.Adapter<UserCfAdapter.UserCfViewHolder>() {
    inner class UserCfViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textSymptom : TextView = itemView.findViewById(R.id.textView_symptom)
        val chipGroup : ChipGroup = itemView.findViewById(R.id.chip_group)
    }
    private val selectedValues: MutableList<Double?> = MutableList(data.size) { null }
    private val namaGejalaList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usercf,parent,false)
        return UserCfViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserCfViewHolder, position: Int) {
        val dataUserCf = data[position]
        holder.textSymptom.text = dataUserCf
        namaGejalaList.add(dataUserCf)

        holder.chipGroup.clearCheck()

        val selectedValue = selectedValues[position]
        for (i in 0 until holder.chipGroup.childCount) {
            val chip = holder.chipGroup.getChildAt(i) as Chip
            val nilaiCertainty = getNilaiCertainty(i)
            chip.isChecked = selectedValue == nilaiCertainty

            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedValues[position] = nilaiCertainty
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }


    private fun getNilaiCertainty(position: Int): Double {
        return when (position) {
            0 -> 0.25
            1 -> 0.5
            2 -> 0.75
            3 -> 1.0
            else -> 0.0
        }
    }

    fun getSelectedValues(): List<Gejala> {
        var gejalaDataList = mutableListOf<Gejala>()
        for (i in 0 until namaGejalaList.size) {
            val namaGejala = namaGejalaList[i]
            val nilaiCertainty = selectedValues[i] ?: 0.0
            val gejalaData = Gejala(namaGejala, nilaiCertainty)
            gejalaDataList.add(gejalaData)
        }
        return gejalaDataList
    }
    fun getNamaGejalaList(): List<String> {
        return namaGejalaList
    }
    fun getSelectedValues2(): List<Double?> {
        return selectedValues
    }
    fun isAnyChipSelected(): Boolean {
        return selectedValues.any { it != null }
    }
}