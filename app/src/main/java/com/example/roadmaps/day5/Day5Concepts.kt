package com.example.roadmaps.day5

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roadmaps.R
import com.example.roadmaps.databinding.FragmentLandmarkDetailBinding
import com.example.roadmaps.databinding.FragmentLandmarkListBinding
import com.example.roadmaps.databinding.ListItemLandmarkBinding

/**
 * ======================================================================================
 * DAY 5 – NAVIGATION & FRAGMENTS (DETAILED CONCEPTS)
 * ======================================================================================
 */

//--------------------------------------------------------------------------------------
// CONCEPT 1: FRAGMENT & SINGLE-ACTIVITY ARCHITECTURE
//--------------------------------------------------------------------------------------
/**
 * A `Fragment` is a reusable, modular portion of your app's UI. Think of it as a
 * "mini-activity" that must be hosted within an actual `Activity`.
 *
 * The modern standard for Android apps is the **Single-Activity Architecture**. Instead of
 * having many activities, you have one main `Activity` that acts as a container. Different
 * screens are then implemented as `Fragment`s that are swapped in and out of this container.
 *
 * INTERVIEW FOCUS (Why use this architecture?):
 * - Simpler Backstack: Managing the "back" button behavior is much easier with a single
 *   stack of fragments than with multiple stacks of activities.
 * - Shared UI Elements: It's much easier to implement a persistent UI element (like a
 *   bottom navigation bar or a toolbar) in the main activity and have it visible across
 *   all fragment screens.
 * - Smoother Transitions: Swapping fragments is generally lighter-weight and results in
 *   smoother animations than launching new activities.
 * - Reusability: The same fragment can be reused in different parts of your app or in
 *   different screen layouts (e.g., for tablets).
 */

//--------------------------------------------------------------------------------------
// CONCEPT 2: THE FRAGMENT LIFECYCLE & VIEW BINDING
//--------------------------------------------------------------------------------------
/**
 * The Fragment lifecycle is more complex than an Activity's because it has two separate
 * lifecycles to manage: the Fragment's own lifecycle and the lifecycle of its View.
 *
 * INTERVIEW FOCUS (Key Lifecycle Methods for UI):
 * - `onCreate(savedInstanceState: Bundle?)`: The fragment instance is created. This is for
 *   non-UI initialization.
 * - `onCreateView(...)`: The system calls this to inflate the fragment's layout from XML.
 *   You must return the root `View` of your layout here.
 * - `onViewCreated(view: View, ...)`: Called IMMEDIATELY after `onCreateView()` returns.
 *   The `view` parameter is the root view you just created. This is the SAFEST and best
 *   place to do any UI setup, like finding views, setting click listeners, or configuring
 *   a RecyclerView, because it guarantees the view hierarchy is fully created and not yet
 *   destroyed.
 * - `onDestroyView()`: Called when the fragment's view is being torn down. This is CRITICAL.
 *   If you hold references to views (like with ViewBinding), you MUST clear them here to
 *   prevent memory leaks. The fragment instance can outlive its view, so holding onto a
 *   reference to a destroyed view is a major source of bugs.
 *
 * The `_binding`/`binding` pattern you see below is the official recommended way to handle
 * ViewBinding safely in fragments. `_binding` is nullable and is set to null in `onDestroyView()`.
 * The `binding` property is a non-nullable getter that will throw an exception if you try
 * to access the view after it has been destroyed, which is safer than getting a crash later.
 */

//--------------------------------------------------------------------------------------
// CONCEPT 3: THE NAVIGATION COMPONENT
//--------------------------------------------------------------------------------------
/**
 * The Jetpack Navigation Component is a framework for implementing navigation in your app.
 * It simplifies the process and provides a consistent, predictable user experience.
 *
 * INTERVIEW FOCUS (The Three Pillars of Navigation):
 * 1. `Navigation Graph` (`nav_graph.xml`): A visual XML file that acts as the single source
 *    of truth for your app's navigation. It defines all your screens (destinations, which
 *    are our fragments) and the paths between them (actions).
 * 2. `NavHostFragment`: A special widget you place in your `MainActivity`'s layout. It's an
 *    empty container that displays the different fragment destinations from your NavGraph.
 * 3. `NavController`: An object that manages the navigation within the `NavHost`. You get an
 *    instance of it within a fragment by calling `findNavController()`. You then tell it to
 *    navigate by passing it an `action` ID from your NavGraph.
 */

//--------------------------------------------------------------------------------------
// CONCEPT 4: SAFE ARGS (TYPE-SAFE ARGUMENT PASSING)
//--------------------------------------------------------------------------------------
/**
 * `SafeArgs` is a Gradle plugin that generates code to ensure you pass data between fragments
 * in a type-safe way, preventing common runtime crashes.
 *
 * INTERVIEW FOCUS (How it works and why it's better than Bundles):
 * - How it Works: You define arguments for a destination in your `nav_graph.xml`, specifying
 *   their name and type (e.g., an argument named "landmark" of type `com.example.roadmaps.day5.Landmark`).
 * - Code Generation: When you build the project, the plugin generates two classes:
 *   1. `[SourceFragment]Directions`: A class for the fragment you are navigating FROM. It gives
 *      you a method (e.g., `actionListFragmentToDetailFragment(myLandmark)`) that takes your
 *      data as a typed argument and returns a `NavDirections` object.
 *   2. `[DestinationFragment]Args`: A class for the fragment you are navigating TO. You use the
 *      `by navArgs()` delegate to get a type-safe reference to the arguments.
 * - The Benefit: If you try to pass the wrong type of data, or if you rename an argument in XML
 *   but not in your code, your project will fail to compile. This catches bugs at build time
 *   instead of causing a crash for your users.
 */

// --- IMPLEMENTATION ---

data class Landmark(val name: String, val location: String, val description: String) : java.io.Serializable

fun createSampleData() = listOf(
    Landmark("Eiffel Tower", "Paris, France", "A wrought-iron lattice tower on the Champ de Mars."),
    Landmark("Colosseum", "Rome, Italy", "An oval amphitheatre in the centre of the city of Rome."),
    Landmark("Statue of Liberty", "New York, USA", "A colossal neoclassical sculpture on Liberty Island.")
)

class LandmarkListFragment : Fragment(R.layout.fragment_landmark_list) {

    private var _binding: FragmentLandmarkListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLandmarkListBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = LandmarkAdapter(createSampleData()) { selectedLandmark ->
            // Using the generated Directions class for type-safe navigation
            val action = LandmarkListFragmentDirections.actionListFragmentToDetailFragment(selectedLandmark)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // CRITICAL: Clear the binding reference to prevent memory leaks.
    }
}

class LandmarkDetailFragment : Fragment(R.layout.fragment_landmark_detail) {

    private var _binding: FragmentLandmarkDetailBinding? = null
    private val binding get() = _binding!!

    // Using the generated Args class to receive the argument in a type-safe way
    private val args: LandmarkDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLandmarkDetailBinding.bind(view)

        val landmark = args.landmark
        requireActivity().title = landmark.name
        binding.detailLocation.text = landmark.location
        binding.detailDescription.text = landmark.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class LandmarkAdapter(
    private val landmarks: List<Landmark>,
    private val onClick: (Landmark) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    inner class LandmarkViewHolder(private val binding: ListItemLandmarkBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(landmark: Landmark) {
            binding.landmarkName.text = landmark.name
            binding.landmarkLocation.text = landmark.location
            binding.root.setOnClickListener { onClick(landmark) }
        }
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): LandmarkViewHolder {
        val binding = ListItemLandmarkBinding.inflate(android.view.LayoutInflater.from(parent.context), parent, false)
        return LandmarkViewHolder(binding)
    }

    override fun getItemCount(): Int = landmarks.size

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        holder.bind(landmarks[position])
    }
}
