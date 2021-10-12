package com.example.workoutdiary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val workoutId: Long,
    val name: String,
    val date: String,
    val duration: Long,
    val mood: String,
    val exercises: String,
)