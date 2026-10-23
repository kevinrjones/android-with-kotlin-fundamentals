package com.pluralsight.dailytasks.data

data class Task(
    val id: Int,
    val title: String,
    val detail: String?,
    val isComplete: Boolean = false
)

val sampleTasks = listOf(
    Task(1, "Buy groceries", "Pick up fruit, milk, and bread"),
    Task(2, "Read Kotlin chapter", "Review null safety and data classes"),
    Task(3, "Walk the dog", detail = null),
    Task(4, "Water the plants", "Kitchen herbs and balcony pots"),
    Task(5, "Reply to team chat", detail = null),
    Task(6, "Prep lunch", "Chop veggies and pack a container", isComplete = true),
    Task(7, "Stretch for ten minutes", detail = null),
    Task(8, "Review pull request", "Leave comments on the Home screen changes"),
    Task(9, "Charge headphones", detail = null, isComplete = true),
    Task(10, "Schedule dentist", "Call before Friday afternoon"),
    Task(11, "Backup photos", detail = null),
    Task(12, "Skim Compose notes", "Modifiers, state, and LazyColumn"),
    Task(13, "Take out recycling", detail = null),
    Task(14, "Plan weekend hike", "Check weather and trail map"),
    Task(15, "Tidy desk", "Clear cables and file loose papers", isComplete = true)
)

var nextId = 16
