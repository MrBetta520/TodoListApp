package com.example.simpletodo.ui.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpletodo.data.Todo

@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit,
){
    val viewModel = viewModel(DetailViewModel::class.java, factory = DetailViewModelFactory(selectedId))
    val state by viewModel.state.collectAsState()
    DetailScreenComponent(
        todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it)},
        timeText = state.time,
        onTimeTextChange = { viewModel.onTimeChange(it)},
        onNavigate = { onNavigate() },
        onSaveTodo = { viewModel.insert(it)},
        selectedId = state.selectId
    )
}

@Composable
fun DetailScreenComponent(
    todoText: String,
    onTodoTextChange: (String) -> Unit,
    timeText: String,
    onTimeTextChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTodo: (Todo) -> Unit,
    selectedId: Long
) {
    val isTodoEdit = selectedId == -1L
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = todoText,
            onValueChange = { onTodoTextChange(it) },
            label = { Text(text = "Enter Todo")}
        )

        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = timeText,
            onValueChange = { onTimeTextChange(it) },
            label = { Text(text = "Enter Time")}
        )

        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val todo = if (isTodoEdit) Todo(todoText, timeText)
            else Todo(todoText, timeText, id = selectedId)
            onSaveTodo(todo)
            onNavigate()
        }) {
            val text = if (isTodoEdit) "Save Todo" else "Update todo"
            Text(text = text)
        }

    }

}