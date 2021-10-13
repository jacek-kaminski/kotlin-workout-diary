package com.example.workoutdiary.database

import androidx.room.Entity


@Entity(primaryKeys = ["workoutId", "exerciseId"])
data class WorkoutExerciseCrossRef(
    val workoutId: Long,
    val exerciseId: Long
)
