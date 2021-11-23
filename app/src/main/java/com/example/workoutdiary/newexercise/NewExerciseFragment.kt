package com.example.workoutdiary.newexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workoutdiary.R
import com.example.workoutdiary.database.AppDatabase
import com.example.workoutdiary.databinding.FragmentNewExerciseBinding
import com.example.workoutdiary.enums.ExerciseType
import com.google.android.material.snackbar.Snackbar

class NewExerciseFragment : Fragment() {

    lateinit var binding: FragmentNewExerciseBinding
    lateinit var newExerciseViewModel: NewExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_exercise, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).exerciseDao
        val viewModelFactory = NewExerciseViewModelFactory(dataSource)

        newExerciseViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewExerciseViewModel::class.java)

        binding.addNewExerciseButton.setOnClickListener { this.onAddNewExerciseClick() }

        val spinner: Spinner = binding.exerciseTypeSpinner

        this.context?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                ExerciseType.stringValues()
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }

        newExerciseViewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.exercise_added_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                newExerciseViewModel.doneShowingSnackbar()
            }
        })

        newExerciseViewModel.navigateToExerciseList.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(NewExerciseFragmentDirections.actionNewExerciseFragmentToExerciseListFragment())
                newExerciseViewModel.doneNavigating()
            }
        })

        return binding.root
    }

    private fun onAddNewExerciseClick() {
        newExerciseViewModel.addNewExercise(
            binding.exerciseName.text.toString(),
            ExerciseType.values()
                .firstOrNull { it.value == binding.exerciseTypeSpinner.selectedItem.toString() }!!,
            binding.exerciseDuration.text.toString().toLong()
        )
    }

    private fun areInputDataValid(): Boolean {
        return true
    }
}

