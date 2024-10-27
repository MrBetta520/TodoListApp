package com.example.simpletodo.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpletodo.data.Todo
import com.example.simpletodo.ui.home.components.TodoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(onNavigate:(Todo?) -> Unit) {
    val viewModel = viewModel(HomeViewModel::class.java )
    val state by viewModel.state.collectAsState()
    
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onNavigate(null) }) {
            Icon( imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        LazyColumn() {
            items(state.todoList.size) { index ->
                val listIndex = state.todoList[index]
                TodoItem(
                    todo = listIndex,
                    onChecked = {viewModel.updateTodo(it, listIndex.id)},
                    onDelete = {viewModel.delete(it)},
                    onNavigation = { onNavigate(it)})
            }
        }

    }
}