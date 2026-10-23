package com.pluralsight.dailytasks.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pluralsight.dailytasks.data.Task
import com.pluralsight.dailytasks.data.sampleTasks

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(sampleTasks)
        private set

    fun addTask(title: String) {
        if (title.isBlank()) return
        val newTask = Task(
            id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            detail = null
        )
        tasks = tasks + newTask
    }

    fun deleteTask(task: Task) {
        tasks = tasks.filter { it.id != task.id }
    }

    fun toggleTask(task: Task) {
        tasks = tasks.map {
            if(it.id == task.id) it.copy(isComplete = !it.isComplete) else it
        }
    }

}