package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluralsight.dailytasks.R
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks

@Composable
fun TaskRow(
    task: Task,
    onDeleteTask: (Task) -> Unit = {},
    onToggleTask: (Task) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { println(task) }
            .padding(vertical = 12.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                checked = task.isComplete,
                modifier = Modifier.align(Alignment.CenterStart),
                onCheckedChange = {
                    onToggleTask(task)
                })
            Text(
                task.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center)
            )
            IconButton(
                onClick = {
                    onDeleteTask(task)
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete task",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Text(
            text = task.detail ?: "No additional details",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp)
        )
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