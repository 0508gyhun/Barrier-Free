package com.example.barrier_free_restaurant

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.barrier_free_restaurant.databinding.ActivityHomeBinding

class HomeActivity:AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.homeNavHostFragment.id) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.homeBottomNavigation.setupWithNavController(navController)
    }

}