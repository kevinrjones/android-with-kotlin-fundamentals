package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.nextId
import com.pluralsight.dailytasks.data.sampleTasks
import com.pluralsight.dailytasks.navigation.TaskDetailsScreen
import com.pluralsight.dailytasks.navigation.TaskListScreen

@Composable
fun DailyTasksApp(
    viewModel: TaskViewModel = viewModel()
) {
    val navController = rememberNavController()
    val tasks = viewModel.tasks
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(navController = navController, startDestination = TaskListScreen) {
            composable<TaskListScreen> {
                HomeScreen(
                    tasks,
                    modifier = Modifier.padding(innerPadding),
                    onAddTask = { title ->
                        viewModel.addTask(title)
                    },
                    onDeleteTask = { task ->
                        viewModel.deleteTask(task)
                    },
                    onToggleTask = { task ->
                        viewModel.toggleTask(task)
                    },
                    onTaskClick = { task ->
                        navController.navigate(TaskDetailsScreen(task.id))
                    }
                )
            }

            composable<TaskDetailsScreen> { navBackStackEntry ->
                val args: TaskDetailsScreen = navBackStackEntry.toRoute<TaskDetailsScreen>()
                val task = tasks.find {it.id == args.taskId}
                TaskDetailScreen(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(color = MaterialTheme.colorScheme.background),
                    task = task,
                    onToggleComplete = viewModel::toggleTask,
                    onBack = navController::popBackStack
                )
            }
        }
    }
}