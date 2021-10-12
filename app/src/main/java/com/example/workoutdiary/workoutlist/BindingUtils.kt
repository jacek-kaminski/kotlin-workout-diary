package com.example.workoutdiary.workoutlist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.workoutdiary.database.Workout

class BindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("workoutName")
        fun TextView.setWorkoutName(item: Workout) {
            text = item.name
        }

        @JvmStatic
        @BindingAdapter("workoutDate")
        fun TextView.setWorkoutDate(item: Workout) {
            text = item.date
        }
    }
}