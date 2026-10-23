package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks

@Composable
fun TaskRow(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { println(task) }
            .padding(vertical = 12.dp)
    ) {
        Text(task.title, style = MaterialTheme.typography.titleMedium)
        Text(if (task.isComplete) "Completed" else "Open")
        Text(task.detail ?: "No additional detail")
    }
}

@Preview
@Composable
fun TaskRowPreview() {
    MaterialTheme {
        TaskRow(sampleTasks[0])
    }
}

@Preview
@Composable
fun TaskRowListPreview() {
    MaterialTheme {
        Column() {
            TaskRow(sampleTasks[0])
            TaskRow(sampleTasks[2])
        }
    }
}