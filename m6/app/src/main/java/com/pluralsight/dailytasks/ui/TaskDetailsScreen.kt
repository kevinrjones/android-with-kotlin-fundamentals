package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluralsight.dailytasks.R
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.navigation.TaskDetailsScreen
import com.pluralsight.dailytasks.ui.theme.DailyTasksTheme

@Composable
fun TaskDetailScreen(
    modifier: Modifier,
    task: Task?,
    onBack: () -> Unit,
    onToggleComplete: (Task) -> Unit
) {
    Column(modifier = modifier.padding(24.dp)) {

        Button(
            onClick = onBack,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) { Text("Back") }
        if (task == null) {
            Text(stringResource(R.string.no_task_selected))
        } else {
            Text(task.title, style = MaterialTheme.typography.headlineSmall)
            Text(task.detail ?: stringResource(R.string.no_details))
            Text(if (task.isComplete) stringResource(R.string.completed_status)  else stringResource(R.string.open_status))
            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ), onClick = { onToggleComplete(task) }) {
                Text(if (task.isComplete) stringResource(R.string.mark_task_open, task.title)
                else stringResource(R.string.mark_task_complete, task.title))
            }
        }
    }
}

@Preview
@Composable
fun TaskDetailsScreenPreview1() {
    DailyTasksTheme() {
        TaskDetailScreen(modifier = Modifier, task = null, onBack = {}, onToggleComplete = {})
    }
}

@Preview
@Composable
fun TaskDetailsScreenPreview2() {
    DailyTasksTheme() {
        TaskDetailScreen(modifier = Modifier, task = Task(1, "Test Title", detail = "Test Details", ), onBack = {}, onToggleComplete = {})
    }
}