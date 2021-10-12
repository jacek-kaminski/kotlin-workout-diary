package com.example.workoutdiary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDatabaseDao {

    @Insert
    suspend fun insert(workout: Workout)

    @Update
    suspend fun update(workout: Workout)

    /**
     * Selects and returns workout with given id.
     *
     * @param workoutId
     */
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    suspend fun find(workoutId: Long): Workout


    /**
     * Selects and returns all workouts.
     *
     */
    @Query("SELECT * FROM workouts")
    fun findAllWorkouts(): LiveData<List<Workout>>
}