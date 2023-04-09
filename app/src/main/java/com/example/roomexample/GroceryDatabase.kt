package com.example.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class], version = 1)
abstract class GroceryDatabase:RoomDatabase() {

    abstract fun groceryDao():GroceryDAO

    companion object{

        @Volatile
        private var INSTANCE:GroceryDatabase?=null

        fun getInstance(context: Context):GroceryDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GroceryDatabase::class.java,
                        "grocery_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}