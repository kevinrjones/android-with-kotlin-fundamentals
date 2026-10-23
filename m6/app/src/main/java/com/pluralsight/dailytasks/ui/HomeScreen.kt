package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluralsight.dailytasks.R
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks
import com.pluralsight.dailytasks.ui.theme.DailyTasksTheme

@Composable
fun HomeScreen(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
    onAddTask: (String) -> Unit = {},
    onToggleTask: (Task) -> Unit = {},
    onDeleteTask: (Task) -> Unit = {},
    onTaskClick: (Task) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            stringResource(R.string.home_subtitle),
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(R.color.task_accent)
        )
        AddTask(onAddTask = onAddTask)

        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.ic_task_calendar),
                        contentDescription = stringResource(R.string.home_calendar_description),
                        modifier = Modifier.size(160.dp)
                    )
                    Text(stringResource(R.string.empty_tasks))
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = tasks, key = { task -> task.id }) { task ->
                    TaskRow(task,
                        onDeleteTask = onDeleteTask,
                        onToggleTask = onToggleTask,
                        onTaskClick = onTaskClick)
                }
            }
        }
    }
}

@Preview()
@Composable
fun HomeScreenPreview() {
    DailyTasksTheme {
        HomeScreen(sampleTasks)
    }
}

@Preview()
@Composable
fun HomeScreenPreviewEx() {
    DailyTasksTheme() {
        HomeScreen(emptyList())
    }
}