package com.example.myfamilyapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Range
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.myfamilyapp.Adapter.ContactAdapter
import com.example.myfamilyapp.Adapter.HomeAdapter
import com.example.myfamilyapp.Model.ContactModel
import com.example.myfamilyapp.Model.ItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Constructor
import kotlin.math.log


class HomeFragment : Fragment() {

    private lateinit var itemAdapter:HomeAdapter
    private lateinit var contactAdapter:ContactAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = listOf<ItemModel>(
            ItemModel("Name1"),
            ItemModel("Name2"),
            ItemModel("Name3"),
            ItemModel("Name4"),
            ItemModel("Name5")
        )








        itemAdapter = HomeAdapter(list)
        recyclerView = view.findViewById(R.id.recyclerView_home)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = itemAdapter

        contactAdapter = ContactAdapter(fetchContacts())

        CoroutineScope(Dispatchers.IO).launch {
            fetchContacts()
            withContext(Dispatchers.Main){
                contactAdapter.notifyDataSetChanged()
                fetchDataFromDataBase()
            }
        }

        recyclerView = view.findViewById(R.id.recyclerView_Contact)
        recyclerView.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = contactAdapter





    }

    private suspend fun fetchDataFromDataBase() {
        val database = ContactDatabase.getDatabase(requireContext())


    }

    @SuppressLint("Range")
    fun fetchContacts():MutableList<ContactModel>{
        val names = mutableListOf<ContactModel>()
        val cr = requireActivity().contentResolver
        val cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        if (cur!!.count > 0){
            while (cur.moveToNext()){
                val name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                names.add(ContactModel(name,number))

            }
        }
        return names

    }



}