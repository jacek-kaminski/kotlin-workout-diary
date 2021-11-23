package com.example.workoutdiary.exerciselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutdiary.database.ExerciseDao
import com.example.workoutdiary.database.Workout
import kotlinx.coroutines.launch

class ExerciseListViewModel(
    dataSource: ExerciseDao
) : ViewModel() {

    val exerciseDao = dataSource

    val exercises = exerciseDao.findAllExercises()

    fun onCreateNewExercise() {
        viewModelScope.launch {
            _navigateToAddExercise.value = true
        }
    }

    private val _navigateToAddExercise = MutableLiveData<Boolean?>()

    val navigateToAddExercise: LiveData<Boolean?>
        get() = _navigateToAddExercise

    fun doneNavigating() {
        _navigateToAddExercise.value = null
    }
}