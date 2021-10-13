package com.example.workoutdiary.workoutlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workoutdiary.database.WorkoutDatabaseDao

/**
 * Provides the WorkoutDatabaseDao and context to the WorkoutListViewModel.
 */
class WorkoutListViewModelFactory(
    private val dataSource: WorkoutDatabaseDao,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutListViewModel::class.java)) {
            return WorkoutListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}