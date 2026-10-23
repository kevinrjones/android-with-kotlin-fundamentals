package com.pluralsight.dailytasks.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    val uiState: StateFlow<TaskUiState>
        field = MutableStateFlow(TaskUiState())

//    private val _uiState = MutableStateFlow(TaskUiState())
//    var uiState  = _uiState.asStateFlow()
//
    fun taskEvent(taskEvent: TaskEvent) {
        when (taskEvent) {
            is ToggleFilterEvent -> toggleFilter(taskEvent.filterState)
            is ToggleTaskEvent -> toggleTask(taskEvent.task)
            is DeleteTaskEvent -> deleteTask(taskEvent.task)
            is AddTaskEvent -> addTask(taskEvent.title)
        }
    }

    private fun toggleFilter(filter: TaskFilter) {
        uiState.update { currentState ->
            currentState.copy(filter = filter)
        }
    }

    private fun addTask(title: String) {
        if (title.isBlank()) return
        uiState.update { currentState ->
            val newTask = Task(
                id = (currentState.tasks.maxOfOrNull { it.id } ?: 0) + 1,
                title = title,
                detail = null
            )

            currentState.copy(tasks = currentState.tasks + newTask)
        }
    }

    private fun deleteTask(task: Task) {
        uiState.update { currentState ->
            currentState.copy(tasks = currentState.tasks.filter { it.id != task.id })
        }
    }

    private fun toggleTask(task: Task) {
        uiState.update { currentState ->
            val updatedTasks = currentState.tasks.map { if (it.id == task.id) it.copy(isComplete = !it.isComplete) else it }
            currentState.copy(tasks = updatedTasks)
        }
    }
}

sealed interface TaskEvent
data class ToggleFilterEvent(val filterState: TaskFilter) : TaskEvent
data class ToggleTaskEvent(val task: Task) : TaskEvent
data class DeleteTaskEvent(val task: Task) : TaskEvent
data class AddTaskEvent(val title: String) : TaskEvent