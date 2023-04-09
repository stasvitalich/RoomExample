package com.example.roomexample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDAO {

    @Insert
    suspend fun insert(listEntity: ListEntity)

    @Update
    suspend fun update(listEntity: ListEntity)

    @Delete
    suspend fun delete(listEntity: ListEntity)

    @Query("SELECT * FROM `grocery-table`")
    fun fetchAllGroceries():Flow<List<ListEntity>>

    @Query("SELECT * FROM `grocery-table` where id=:id")
    fun fetchGroceryById(id:Int):Flow<List<ListEntity>>
}