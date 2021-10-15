package com.example.workoutdiary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao {

    @Insert
    suspend fun insert(workout: Workout)

    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

    /**
     * Selects and returns workout with given id.
     *
     * @param workoutId
     */
    @Query("SELECT * FROM workouts WHERE workoutId = :workoutId")
    suspend fun find(workoutId: Long): Workout


    @Transaction
    @Query("SELECT * FROM workouts WHERE workoutId = :workoutId")
    suspend fun findWorkoutsWithExercises(workoutId: Long): WorkoutWithExercises


    /**
     * Selects and returns all workouts.
     */
    @Query("SELECT * FROM workouts")
    fun findAllWorkouts(): LiveData<List<Workout>>
}