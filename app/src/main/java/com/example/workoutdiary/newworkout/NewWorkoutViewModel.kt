package com.example.workoutdiary.newworkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.database.WorkoutDao
import com.example.workoutdiary.enums.WorkoutMood
import kotlinx.coroutines.launch

class NewWorkoutViewModel(dataSource: WorkoutDao) : ViewModel() {

    val database = dataSource

    private val _navigateToWorkoutList = MutableLiveData<Boolean?>()
    val navigateToWorkoutList: LiveData<Boolean?>
        get() = _navigateToWorkoutList

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackbarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent


    fun addNewWorkout(
        name: String,
        date: String,
        duration: Long,
        exercises: String,
        mood: WorkoutMood
    ) {
        viewModelScope.launch {

            val newWorkout = Workout(0L, name, date, duration, mood, exercises)
            database.insert(newWorkout)
            _navigateToWorkoutList.value = true
            _showSnackbarEvent.value = true
        }
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun doneNavigating() {
        _navigateToWorkoutList.value = null
    }
}