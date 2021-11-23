package com.example.workoutdiary.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutdiary.enums.ExerciseType

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long,
    val name: String,
    val type: ExerciseType,
    val duration: Long,
    val isFavorite: Boolean = false
)