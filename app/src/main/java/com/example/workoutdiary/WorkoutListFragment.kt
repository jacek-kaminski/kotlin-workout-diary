package com.example.workoutdiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.workoutdiary.databinding.FragmentWorkoutListBinding

class WorkoutListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWorkoutListBinding>(
            inflater,
            R.layout.fragment_workout_list,
            container,
            false
        )
//        findViewById(.fab.setOnClickListener { view: View ->
//            view.findNavController()
//                .navigate(R.id.action_workoutListFragment_to_workoutDetailsFragment)
//        }
        // Inflate the layout for this fragment
        return binding.root
    }

}