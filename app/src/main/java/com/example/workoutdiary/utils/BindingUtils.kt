package com.example.workoutdiary.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.workoutdiary.R
import com.example.workoutdiary.database.Exercise
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.enums.ExerciseType
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

        @JvmStatic
        @BindingAdapter("exerciseName")
        fun TextView.setExerciseName(item: Exercise) {
            text = item.name
        }

        @JvmStatic
        @BindingAdapter("exerciseDuration")
        fun TextView.setExerciseDuration(item: Exercise) {
            text = resources.getString(R.string.exercise_duration_format, item.duration)
        }

        @JvmStatic
        @BindingAdapter("exerciseIcon")
        fun ImageView.setExerciseIcon(item: Exercise) {
            when (item.type) {
                ExerciseType.LEGS -> {
                    setImageResource(R.drawable.ic_exercise_legs)
                }
                ExerciseType.BACK -> {
                    setImageResource(R.drawable.ic_exercise_back)
                }
                ExerciseType.CHEST -> {
                    setImageResource(R.drawable.ic_exercise_chest)
                }
                ExerciseType.ARMS -> {
                    setImageResource(R.drawable.ic_exercise_arms)
                }
                ExerciseType.BICEPS -> {
                    setImageResource(R.drawable.ic_exercise_biceps)
                }
                ExerciseType.TRICEPS -> {
                    setImageResource(R.drawable.ic_exercise_triceps)
                }
                ExerciseType.ABS -> {
                    setImageResource(R.drawable.ic_exercise_abs)
                }
                ExerciseType.CARDIO -> {
                    setImageResource(R.drawable.ic_exercise_cardio)
                }
                ExerciseType.RECREATIONAL -> {
                    setImageResource(R.drawable.ic_exercise_recreational)
                }
            }
            setColorFilter(ContextCompat.getColor(context, R.color.black))
        }
    }
}