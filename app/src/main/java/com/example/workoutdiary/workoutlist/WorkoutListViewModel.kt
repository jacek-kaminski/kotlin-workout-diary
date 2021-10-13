package com.example.workoutdiary.workoutlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.database.WorkoutDatabaseDao
import kotlinx.coroutines.launch

class WorkoutListViewModel(
    dataSource: WorkoutDatabaseDao
) : ViewModel() {


    /**
     * Holds a reference to database via WorkoutDatabaseDao.
     */
    val database = dataSource

    val workouts = database.findAllWorkouts()

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackbarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    fun onCreateNewWorkout() {
        viewModelScope.launch {
            _navigateToAddWorkout.value = true
        }
    }

    fun onDeleteNewWorkout(workout: Workout) {
        viewModelScope.launch {
            database.delete(workout)
            _showSnackbarEvent.value = true
        }
    }

    private val _navigateToAddWorkout = MutableLiveData<Boolean?>()

    val navigateToAddWorkout: LiveData<Boolean?>
        get() = _navigateToAddWorkout

    fun doneNavigating() {
        _navigateToAddWorkout.value = null
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }
}