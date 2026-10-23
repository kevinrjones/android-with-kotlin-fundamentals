package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pluralsight.dailytasks.data.sampleTasks

@Composable
fun DailyTasksApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeScreen(sampleTasks, modifier = Modifier.padding(innerPadding))
    }
}