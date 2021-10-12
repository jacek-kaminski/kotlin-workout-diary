package com.example.workoutdiary.newworkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.database.WorkoutDatabaseDao
import kotlinx.coroutines.launch

class NewWorkoutViewModel(dataSource: WorkoutDatabaseDao) : ViewModel() {

    val database = dataSource
    private val _navigateToWorkoutList = MutableLiveData<Boolean?>()
    val navigateToWorkoutList: LiveData<Boolean?>
        get() = _navigateToWorkoutList


    fun addNewWorkout(name: String, date: String, duration: Long, mood: String, exercises: String) {
        viewModelScope.launch {

            val newWorkout = Workout(0L, name, date, duration, mood, exercises)
            database.insert(newWorkout)
            _navigateToWorkoutList.value = true
        }
    }

    fun doneNavigating() {
        _navigateToWorkoutList.value = null
    }
}