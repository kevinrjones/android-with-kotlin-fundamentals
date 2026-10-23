package com.pluralsight.dailytasks.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskListScreen

@Serializable
data class TaskDetailsScreen(val taskId: Int)