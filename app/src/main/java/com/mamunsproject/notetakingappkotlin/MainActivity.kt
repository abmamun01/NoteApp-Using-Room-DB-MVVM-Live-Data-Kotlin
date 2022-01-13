package com.mamunsproject.notetakingappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navControllerVar: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navControllerVar = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navControllerVar)

    }


    override fun onNavigateUp(): Boolean {
        return navControllerVar.navigateUp() || super.onNavigateUp()
    }
}