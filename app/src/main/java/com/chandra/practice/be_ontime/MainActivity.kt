package com.chandra.practice.be_ontime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.chandra.practice.be_ontime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)

    }


    // Override in activity
    override fun onSupportNavigateUp() : Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}