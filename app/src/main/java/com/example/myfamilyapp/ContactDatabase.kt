package com.example.myfamilyapp

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfamilyapp.Model.ContactModel

@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
public abstract class ContactDatabase:RoomDatabase() {

   abstract fun contactDao():Dao

   companion object{
       @Volatile //It can access for any thread
       private var INSTANCE:ContactDatabase? = null
       fun getDatabase(context: Context):ContactDatabase{
           INSTANCE?.let {
               return it
           }
           return synchronized(ContactDatabase::class.java){
               val instance = androidx.room.Room.databaseBuilder(context.applicationContext,
                   ContactDatabase::class.java,
                   "Contact_DataBase").build()

               INSTANCE = instance
               instance

           }
       }
   }
}