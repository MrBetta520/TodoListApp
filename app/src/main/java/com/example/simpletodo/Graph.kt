package com.example.simpletodo

import android.content.Context
import com.example.simpletodo.data.TodoDataSource
import com.example.simpletodo.data.room.TodoDatabase

object Graph {
    lateinit var database: TodoDatabase
        private set

    val todoRepo by lazy {
        TodoDataSource(database.todoDao())
    }

    fun provide(context: Context) {
        database = TodoDatabase.getDatabase(context)
    }
}