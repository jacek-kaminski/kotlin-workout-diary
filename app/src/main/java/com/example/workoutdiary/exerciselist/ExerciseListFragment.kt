package com.example.workoutdiary.exerciselist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workoutdiary.R
import com.example.workoutdiary.database.AppDatabase
import com.example.workoutdiary.database.Workout
import com.example.workoutdiary.databinding.FragmentExerciseListBinding
import com.example.workoutdiary.databinding.FragmentWorkoutListBinding
import com.google.android.material.snackbar.Snackbar

class ExerciseListFragment : Fragment(), OnExerciseListItemActionListener {

    private lateinit var exerciseListViewModel: ExerciseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentExerciseListBinding>(
            inflater,
            R.layout.fragment_exercise_list,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).exerciseDao
        val viewModelFactory = ExerciseListViewModelFactory(dataSource)

        exerciseListViewModel =
            ViewModelProvider(this, viewModelFactory).get(ExerciseListViewModel::class.java)
        binding.exerciseListViewModel = exerciseListViewModel

        val adapter = ExerciseListAdapter(this)
        binding.exercisesList.adapter = adapter

        exerciseListViewModel.exercises.observe(
            viewLifecycleOwner,
            { it?.let { adapter.submitList(it) } })

        binding.lifecycleOwner = this

        exerciseListViewModel.navigateToAddExercise.observe(viewLifecycleOwner, {
            it?.let {

                this.findNavController()
                    .navigate(ExerciseListFragmentDirections.actionExerciseListFragmentToNewExerciseFragment())
                exerciseListViewModel.doneNavigating()
            }
        })

//        exerciseListViewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
//            if (it == true) {
//                Snackbar.make(
//                    requireActivity().findViewById(android.R.id.content),
//                    getString(R.string.workout_removed_message),
//                    Snackbar.LENGTH_SHORT
//                ).show()
//                exerciseListViewModel.doneShowingSnackbar()
//            }
//        })
        return binding.root
    }

    override fun onItemDelete(workout: Workout) {
        TODO("Not yet implemented")
    }
}