package com.example.workoutdiary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {

    @Insert
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    /**
     * Selects and returns all exercises.
     */
    @Query("SELECT * FROM exercises")
    fun findAllExercises(): LiveData<List<Exercise>>

}