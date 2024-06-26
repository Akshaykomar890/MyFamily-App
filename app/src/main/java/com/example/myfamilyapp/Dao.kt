package com.example.myfamilyapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfamilyapp.Model.ContactModel


@Dao
interface Dao {  //data access object
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactModel: ContactModel)
    @Query("SELECT * FROM ContactModel")
    suspend fun getAll():List<ContactModel>


}