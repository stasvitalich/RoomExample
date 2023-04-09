package com.example.roomexample

import android.app.Application

class GroceryApp:Application() {
    val db by lazy {
        GroceryDatabase.getInstance(this)
    }
}