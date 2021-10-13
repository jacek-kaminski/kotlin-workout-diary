package com.example.workoutdiary.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.workoutdiary.R
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.enums.WorkoutMood

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

        @JvmStatic
        @BindingAdapter("workoutMoodIcon")
        fun ImageView.setWorkoutMoodIcon(item: Workout) {
            when (item.mood) {
                WorkoutMood.GREAT -> {
                    setImageResource(R.drawable.ic_mood_happy_love)
                    setColorFilter(ContextCompat.getColor(context, R.color.mood_great))
                }
                WorkoutMood.GOOD -> {
                    setImageResource(R.drawable.ic_mood_happy)
                    setColorFilter(ContextCompat.getColor(context, R.color.mood_good))
                }
                WorkoutMood.AVERAGE -> {
                    setImageResource(R.drawable.ic_mood_bored)
                    setColorFilter(ContextCompat.getColor(context, R.color.mood_average))
                }
                WorkoutMood.BAD -> {
                    setImageResource(R.drawable.ic_mood_sad)
                    setColorFilter(ContextCompat.getColor(context, R.color.mood_bad))
                }
                WorkoutMood.TERRIBLE -> {
                    setImageResource(R.drawable.ic_mood_crying)
                    setColorFilter(ContextCompat.getColor(context, R.color.mood_terrible))
                }
            }
        }
    }
}