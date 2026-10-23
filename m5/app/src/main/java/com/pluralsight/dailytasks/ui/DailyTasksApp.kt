package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.nextId
import com.pluralsight.dailytasks.data.sampleTasks

@Composable
fun DailyTasksApp(
    viewModel: TaskViewModel = viewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeScreen(
            viewModel.tasks,
            modifier = Modifier.padding(innerPadding),
            onAddTask = { title ->
                viewModel.addTask(title)
            },
            onDeleteTask = { task ->
                viewModel.deleteTask(task)
            },
            onToggleTask = { task ->
                viewModel.toggleTask(task)
            }
        )
    }
}