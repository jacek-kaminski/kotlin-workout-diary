package com.example.workoutdiary.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutdiary.enums.WorkoutMood

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Long,
    val name: String,
    val date: String,
    val duration: Long,
    val mood: WorkoutMood,
    val exercises: String,
)