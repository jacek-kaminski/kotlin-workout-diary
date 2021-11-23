package com.example.workoutdiary.newexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workoutdiary.database.ExerciseDao
import com.example.workoutdiary.database.WorkoutDao

/**
 * Provides the WorkoutDatabaseDao and context to the NewWorkoutViewModel.
 */
class NewExerciseViewModelFactory(private val dataSource: ExerciseDao) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewExerciseViewModel::class.java)) {
            return NewExerciseViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}