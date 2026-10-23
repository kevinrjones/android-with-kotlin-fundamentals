package com.pluralsight.dailytasks.ui

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks

data class TaskUiState(
    val tasks: List<Task> = sampleTasks,
    val filter: TaskFilter = TaskFilter.All
) {
    val visibleTasks: List<Task>
        get() = when (filter) {
            TaskFilter.All -> tasks
            TaskFilter.Open -> tasks.filterNot(Task::isComplete)
            TaskFilter.Completed -> tasks.filter(Task::isComplete)
        }
}

class TaskViewModel : ViewModel() {

    var uiState by mutableStateOf(TaskUiState())

//    var tasks by mutableStateOf(sampleTasks)
//        private set
//
//    var filterState by mutableStateOf(TaskFilter.All)
//        private set
//
//    val visibleTasks by derivedStateOf {
//        when (filterState) {
//            TaskFilter.All -> tasks
//            TaskFilter.Open -> tasks.filterNot(Task::isComplete)
//            TaskFilter.Completed -> tasks.filter(Task::isComplete)
//        }
//    }

    fun toggleFilter(filter: TaskFilter) {
        uiState = uiState.copy(filter = filter)
    }

    fun addTask(title: String) {
        if (title.isBlank()) return
        val newTask = Task(
            id = (uiState.tasks.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            detail = null
        )
        uiState = uiState.copy(tasks = uiState.tasks + newTask)
    }

    fun deleteTask(task: Task) {
        uiState = uiState.copy(
            tasks = uiState.tasks.filter { it.id != task.id }
        )
    }

    fun toggleTask(task: Task) {
        uiState = uiState.copy(
            tasks = uiState.tasks.map {
                if (it.id == task.id) it.copy(isComplete = !it.isComplete) else it
            }
        )
    }

}