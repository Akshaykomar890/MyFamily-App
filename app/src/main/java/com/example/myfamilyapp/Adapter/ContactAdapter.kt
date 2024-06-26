package com.example.myfamilyapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myfamilyapp.Model.ContactModel
import com.example.myfamilyapp.R

class ContactAdapter(val item:List<ContactModel>):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val textView:TextView = view.findViewById(R.id.contactName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_contact,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return item.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val get = item[position]
        holder.textView.text = get.name
    }
}