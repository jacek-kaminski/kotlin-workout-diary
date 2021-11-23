package com.example.workoutdiary.exerciselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutdiary.database.Exercise
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.databinding.ListItemExerciseBinding
import com.example.workoutdiary.databinding.ListItemWorkoutBinding

/**
 *  Adapter for the exercise list.
 */
class ExerciseListAdapter(private val actionListener: OnExerciseListItemActionListener) :
    ListAdapter<Exercise, ExerciseListAdapter.ViewHolder>(ExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, actionListener)
    }

    class ViewHolder private constructor(private val binding: ListItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Exercise, onListItemActionListener: OnExerciseListItemActionListener) {
            binding.exercise = item
//            binding.deleteButton.setOnClickListener { onListItemActionListener.onItemDelete(item) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemExerciseBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.exerciseId == newItem.exerciseId
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}

//todo to implemented

interface OnExerciseListItemActionListener {

    /**
     * Performs an action on delete button click.
     */
    fun onItemDelete(workout: Workout)
}