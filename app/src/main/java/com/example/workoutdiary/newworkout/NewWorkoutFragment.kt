package com.example.workoutdiary.newworkout

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workoutdiary.R
import com.example.workoutdiary.database.AppDatabase
import com.example.workoutdiary.databinding.FragmentNewWorkoutBinding
import com.example.workoutdiary.enums.WorkoutMood
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class NewWorkoutFragment : Fragment() {

    lateinit var binding: FragmentNewWorkoutBinding
    lateinit var newWorkoutViewModel: NewWorkoutViewModel
    lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_workout, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).workoutDao
        val viewModelFactory = NewWorkoutViewModelFactory(dataSource)

        setHasOptionsMenu(false)
        activity?.actionBar?.setIcon(R.drawable.ic_delete)

        newWorkoutViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewWorkoutViewModel::class.java)

        binding.newWorkoutViewModel = newWorkoutViewModel

        binding.addNewWorkoutButton.setOnClickListener { this.onAddNewReworkClick() }
        binding.workoutDate.setOnClickListener { this.onEditWorkoutDateClick() }

        calendar = Calendar.getInstance()

        newWorkoutViewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.workout_added_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                newWorkoutViewModel.doneShowingSnackbar()
            }
        })

        newWorkoutViewModel.navigateToWorkoutList.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(NewWorkoutFragmentDirections.actionNewWorkoutFragmentToWorkoutListFragment())
                newWorkoutViewModel.doneNavigating()
            }
        })

        initMoodSelectors()

        binding.selectedMood = null

        return binding.root
    }

    private fun initMoodSelectors() {
        binding.apply {
            moodItemGreat.setOnClickListener { toggleMood(WorkoutMood.GREAT) }
            moodItemGood.setOnClickListener { toggleMood(WorkoutMood.GOOD) }
            moodItemAverage.setOnClickListener { toggleMood(WorkoutMood.AVERAGE) }
            moodItemBad.setOnClickListener { toggleMood(WorkoutMood.BAD) }
            moodItemTerrible.setOnClickListener { toggleMood(WorkoutMood.TERRIBLE) }
        }
    }

    private fun toggleMood(mood: WorkoutMood) {
        binding.selectedMood = if (binding.selectedMood == mood) null else mood
    }

    private fun onAddNewReworkClick() {
        if (areInputDataValid()) {
            newWorkoutViewModel.addNewWorkout(
                binding.workoutName.text.toString(),
                binding.workoutDate.text.toString(),
                binding.workoutDuration.text.toString().toLong(),
                binding.workoutExercises.text.toString(),
                binding.selectedMood!!,
            )
        } else {
            return
        }
    }

    private fun areInputDataValid(): Boolean {
        return true
    }

    private fun onEditWorkoutDateClick() {
        context?.let {
            DatePickerDialog(
                it, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.GERMAN)
            binding.workoutDate.setText(sdf.format(calendar.time))

        }
}

