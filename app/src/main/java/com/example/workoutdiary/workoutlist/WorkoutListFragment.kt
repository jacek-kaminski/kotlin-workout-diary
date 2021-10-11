package com.example.workoutdiary.workoutlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutdiary.R
import com.example.workoutdiary.database.AppDatabase
import com.example.workoutdiary.databinding.FragmentWorkoutListBinding
import com.google.android.material.snackbar.Snackbar

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
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).workoutDatabaseDao
        val viewModelFactory = WorkoutListViewModelFactory(dataSource, application)

        val workoutListViewModel =
            ViewModelProvider(this, viewModelFactory).get(WorkoutListViewModel::class.java)

        binding.workoutListViewModel = workoutListViewModel

        // Add an Observer on the state variable for showing a Snackbar message
        // when the CLEAR button is pressed.
        workoutListViewModel.showSnackbarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.workout_added_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the toast is only shown once, even if the device
                // has a configuration change.
                workoutListViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }

}