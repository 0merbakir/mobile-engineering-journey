package com.example.roadmaps.day3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.roadmaps.databinding.ActivityFirstBinding
import com.example.roadmaps.databinding.ActivitySecondBinding

/**
 * ======================================================================================
 * DAY 3 – ANDROID FUNDAMENTALS
 * ======================================================================================
 */

//--------------------------------------------------------------------------------------
// 1. WHAT IS AN ACTIVITY?
//--------------------------------------------------------------------------------------
/**
 * An `Activity` is a single, focused thing that the user can do. It represents one screen
 * with a user interface. An application is usually composed of multiple activities that are
 * loosely bound to each other.
 *
 * Interview Focus:
 * - It's the entry point for interacting with the user.
 * - It takes care of creating a window for you in which you can place your UI.
 * - The `AppCompatActivity` is a base class used for activities that use the Support
 *   Library action bar features.
 */

//--------------------------------------------------------------------------------------
// 2. ACTIVITY LIFECYCLE
//--------------------------------------------------------------------------------------
/**
 * The lifecycle is a set of callbacks that the Android system calls to notify you of a
 * change in the activity's state. You MUST know this order for any interview.
 *
 * onCreate() -> onStart() -> onResume() -> (Activity is running)
 * onPause() -> onStop() -> onDestroy()
 *
 * Interview Focus:
 * - `onCreate()`: Called once. Do all your essential setup here: set the layout,
 *   initialize variables, set up click listeners. (MOST IMPORTANT)
 * - `onResume()`: The activity is in the foreground and the user can interact with it.
 * - `onPause()`: The activity is about to go into the background (e.g., another activity
 *   comes on top). Good place to save unsaved data.
 * - `onDestroy()`: Called before the activity is destroyed. Do final cleanup here.
 */

//--------------------------------------------------------------------------------------
// 3. WHAT IS CONTEXT?
//--------------------------------------------------------------------------------------
/**
 * The `Context` is one of the most important but confusing concepts. It is an interface
 * to global information about an application environment. It allows access to
 * application-specific resources and classes, as well as up-calls for
 * application-level operations such as launching activities, broadcasting intents, etc.
 *
 * Interview Focus:
 * - Think of it as the "handle" to the Android system. You need a `Context` to do almost
 *   anything: launch a new activity, get access to resources (strings, colors, assets),
 *   get a system service (like LocationManager), etc.
 * - An `Activity` is a `Context` (Activity inherits from ContextThemeWrapper, which inherits from Context).
 *   That's why you can use `this` inside an Activity when a Context is required.
 */

//--------------------------------------------------------------------------------------
// 4. VIEW BINDING
//--------------------------------------------------------------------------------------
/**
 * `ViewBinding` is a feature that makes it easier to write code that interacts with
 * views. It generates a binding class for each XML layout file. An instance of this
 * binding class contains direct references to all views that have an ID in that layout.
 *
 * Interview Focus:
 * - It replaces `findViewById()`, which is slow and not type-safe.
 * - It's null-safe and type-safe. No more `NullPointerException` from a wrong view ID.
 * - It's faster than `findViewById()` because it avoids a runtime traversal of the view hierarchy.
 */
class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private val TAG = "FirstActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using ViewBinding and set the content view.
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate called")

        // Set up the button click listener to navigate to the second activity.
        binding.navigateToSecondButton.setOnClickListener {
            // An `Intent` is an object used to request an action from another app component.
            // We need a Context (`this`) to create it.
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    // -- Other lifecycle methods for demonstration --
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeText.text = "Welcome to the Second Activity!"
    }
}