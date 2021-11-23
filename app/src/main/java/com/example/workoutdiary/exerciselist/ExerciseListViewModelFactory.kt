package com.example.workoutdiary.exerciselist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workoutdiary.database.ExerciseDao
import com.example.workoutdiary.database.WorkoutDao

/**
 * Provides the WorkoutDatabaseDao and context to the WorkoutListViewModel.
 */
class ExerciseListViewModelFactory(
    private val dataSource: ExerciseDao,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseListViewModel::class.java)) {
            return ExerciseListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}