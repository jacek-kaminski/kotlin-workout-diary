package com.example.workoutdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.workoutdiary.databinding.ActivityMainBinding
import com.example.workoutdiary.workoutlist.WorkoutListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        setFabVisibility(binding.fab)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp()
    }

    private fun setFabVisibility(fab: FloatingActionButton) {
        val currentFragment =
            supportFragmentManager.fragments.find { fragment -> fragment != null && fragment.isVisible }
        when (currentFragment) {
            is WorkoutListFragment ->  fab.visibility = View.VISIBLE
            else -> fab.visibility = View.GONE
        }
    }
}