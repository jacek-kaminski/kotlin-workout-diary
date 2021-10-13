package com.example.workoutdiary.workoutlist

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
import com.example.workoutdiary.databinding.FragmentWorkoutListBinding
import com.google.android.material.snackbar.Snackbar

class WorkoutListFragment : Fragment(), OnListItemActionListener {

    private lateinit var workoutListViewModel: WorkoutListViewModel

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
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).workoutDatabaseDao
        val viewModelFactory = WorkoutListViewModelFactory(dataSource)

        workoutListViewModel =
            ViewModelProvider(this, viewModelFactory).get(WorkoutListViewModel::class.java)

        binding.workoutListViewModel = workoutListViewModel

        val adapter = WorkoutListAdapter(this)
        binding.workoutList.adapter = adapter

        workoutListViewModel.workouts.observe(
            viewLifecycleOwner, { it?.let { adapter.submitList(it) } })

        binding.lifecycleOwner = this

        workoutListViewModel.navigateToAddWorkout.observe(viewLifecycleOwner, {
            it?.let {

                this.findNavController()
                    .navigate(WorkoutListFragmentDirections.actionWorkoutListFragmentToWorkoutDetailsFragment())
                workoutListViewModel.doneNavigating()
            }
        })

        workoutListViewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.workout_removed_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                workoutListViewModel.doneShowingSnackbar()
            }
        })
        return binding.root
    }

    override fun onItemDelete(workout: Workout) {
        workoutListViewModel.onDeleteNewWorkout(workout)
    }
}