package com.example.workoutdiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.workoutdiary.databinding.FragmentWorkoutListBinding

class WorkoutDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWorkoutListBinding>(
            inflater, R.layout.fragment_workout_list, container, false
        )

        return binding.root
    }
}