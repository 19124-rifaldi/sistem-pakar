package com.example.sistempakarikannila.ui.listPenyakit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempakarikannila.R
import com.example.sistempakarikannila.ui.detail_penyakit.DiseaseDetailActivity


class ListPenyakitAdapter(val data : ArrayList<String>):RecyclerView.Adapter<ListPenyakitAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val button :Button = itemView.findViewById(R.id.button_listpenyakit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_penyakit,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val disease = data[position]
        holder.button.text = disease
        holder.button.setOnClickListener {
            val context = holder.button.context as Activity
            val intent = Intent(context,DiseaseDetailActivity::class.java)
            intent.putExtra("diseaseName",disease)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}