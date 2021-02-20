package com.newton.zone.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.newton.zone.R
import kotlinx.android.synthetic.main.frame_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.frame_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.frame_navigation)

        NavigationUI.setupWithNavController(bottom_nav, navController)

        navController.addOnDestinationChangedListener { _,
                                                        destination,
                                                        _ ->
            title = destination.label
        }
    }
}