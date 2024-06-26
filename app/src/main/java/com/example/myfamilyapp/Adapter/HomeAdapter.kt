package com.example.myfamilyapp.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfamilyapp.Model.ItemModel
import com.example.myfamilyapp.R

class HomeAdapter(private val list:List<ItemModel>):RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    class MyViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val textView:TextView = item.findViewById(R.id.each_item_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val connect = LayoutInflater.from(parent.context).inflate(R.layout.each_item_home,parent,false)
        return MyViewHolder(connect)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val item = list[position]
        holder.textView.text = item.name
    }
}