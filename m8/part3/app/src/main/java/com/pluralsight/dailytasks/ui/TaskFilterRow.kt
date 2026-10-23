package com.pluralsight.dailytasks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluralsight.dailytasks.R

@Composable
fun TaskFilterRow(
    selectedFilter: TaskFilter,
    onFilterSelected: (TaskFilter) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        TaskFilter.entries.forEach { filter ->
            FilterChip(
                selected = filter == selectedFilter,
                onClick = {
                    onFilterSelected(filter)
                },
                label = {
                    Text(when(filter) {
                        TaskFilter.All -> stringResource(R.string.filter_all)
                        TaskFilter.Open -> stringResource(R.string.filter_open)
                        TaskFilter.Completed -> stringResource(R.string.filter_completed)
                    })
                }
            )
        }

    }
}

@Preview
@Composable
fun TaskFilterRowPreview() {
    MaterialTheme() {
        TaskFilterRow(TaskFilter.All) { }
    }
}

enum class TaskFilter {All, Open, Completed}