package com.example.workoutdiary.newworkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workoutdiary.database.WorkoutDao

/**
 * Provides the WorkoutDatabaseDao and context to the NewWorkoutViewModel.
 */
class NewWorkoutViewModelFactory(private val dataSource: WorkoutDao) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewWorkoutViewModel::class.java)) {
            return NewWorkoutViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}