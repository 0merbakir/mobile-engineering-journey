package com.example.roadmaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * The original main activity for the project.
 * It is no longer the launch activity and has been converted to a standard
 * AppCompatActivity to remove Jetpack Compose conflicts.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This activity is no longer in use.
        // The launch activity is now day3.FirstActivity.
    }
}
