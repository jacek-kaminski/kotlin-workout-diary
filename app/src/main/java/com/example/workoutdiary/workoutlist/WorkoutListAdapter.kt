package com.example.workoutdiary.workoutlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.databinding.ListItemWorkoutBinding

/**
 *  Adapter for the workout list.
 */
class WorkoutListAdapter(private val actionListener: OnListItemActionListener) :
    ListAdapter<Workout, WorkoutListAdapter.ViewHolder>(WorkoutDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, actionListener)
    }

    class ViewHolder private constructor(private val binding: ListItemWorkoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Workout, onListItemActionListener: OnListItemActionListener) {
            binding.workout = item
            binding.deleteButton.setOnClickListener { onListItemActionListener.onItemDelete(item) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWorkoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class WorkoutDiffCallback : DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem.workoutId == newItem.workoutId
    }

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem == newItem
    }
}


interface OnListItemActionListener {

    /**
     * Performs an action on delete button click.
     */
    fun onItemDelete(workout: Workout)
}