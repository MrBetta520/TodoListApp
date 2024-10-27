package com.example.simpletodo.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simpletodo.data.Todo


@Composable
fun TodoItem(
    todo: Todo,
    onChecked: (Boolean) -> Unit,
    onDelete: (Todo) -> Unit,
    onNavigation: (Todo) -> Unit
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(16.dp)
            .clickable { onNavigation(todo) }) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Checkbox(checked = todo.isComplete, onCheckedChange = { onChecked(it)})
            Spacer(modifier = Modifier.size(16.dp))
            Column( modifier = Modifier.weight(1f)) {
                Text(text = todo.todo, style = MaterialTheme.typography.titleSmall)
                Text(text = todo.time, style = MaterialTheme.typography.bodySmall, color = Color.Black.copy(alpha = 0.5f))
            }
            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = { onDelete(todo) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
        }
    }
}