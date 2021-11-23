package com.example.workoutdiary.newexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutdiary.database.Exercise
import com.example.workoutdiary.database.ExerciseDao
import com.example.workoutdiary.enums.ExerciseType
import kotlinx.coroutines.launch

class NewExerciseViewModel(dataSource: ExerciseDao) : ViewModel() {

    val exercisesDao = dataSource

    private val _navigateToExerciseList = MutableLiveData<Boolean?>()
    val navigateToExerciseList: LiveData<Boolean?>
        get() = _navigateToExerciseList

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackbarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent


    fun addNewExercise(name: String, type: ExerciseType, duration: Long) {
        viewModelScope.launch {
            val newExercise = Exercise(0L, name, type, duration)
            exercisesDao.insert(newExercise)
            _navigateToExerciseList.value = true
            _showSnackbarEvent.value = true
        }
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun doneNavigating() {
        _navigateToExerciseList.value = null
    }
}