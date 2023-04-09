package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery-table")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_of_item")
    val name: String = "",
    @ColumnInfo(name = "quantity_of_item")
    val quantity: String = ""
)
