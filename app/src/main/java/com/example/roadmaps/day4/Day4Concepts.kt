package com.example.roadmaps.day4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadmaps.databinding.ActivityDetailBinding
import com.example.roadmaps.databinding.ActivityListBinding
import com.example.roadmaps.databinding.ListItemLandmarkBinding

/**
 * ======================================================================================
 * DAY 4 – UI & LAYOUT
 * ======================================================================================
 */

//--------------------------------------------------------------------------------------
// 1. RECYCLERVIEW & ADAPTERS
//--------------------------------------------------------------------------------------
/**
 * A `RecyclerView` is the standard, efficient way to display large, scrollable lists of items.
 * It's more advanced than a simple `ListView` because it "recycles" views. Instead of
 * creating a new view for each item, it reuses existing views as they scroll off-screen.
 *
 * Interview Focus:
 * - Key Components: You MUST know the three main parts:
 *   1. `Adapter`: Connects your data (e.g., an `ArrayList`) to the `RecyclerView`. It creates
 *      and binds the views for your items.
 *   2. `ViewHolder`: Holds the view for a single item. The adapter creates these and binds
 *      data to them.
 *   3. `LayoutManager`: Positions the items. `LinearLayoutManager` (vertical/horizontal lists),
 *      `GridLayoutManager` (grids), etc.
 * - Why it's efficient: It only creates as many ViewHolders as needed to fill the screen,
 *   plus a few extra. As you scroll, it rebinds these existing ViewHolders with new data
 *   instead of creating new ones, saving memory and CPU time.
 */

// The data model for our list
data class Landmark(val name: String, val location: String, val description: String) : java.io.Serializable

// The Adapter to connect our data to the RecyclerView
class LandmarkAdapter(
    private val landmarks: List<Landmark>,
    private val onClick: (Landmark) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    // The ViewHolder holds the views for a single list item.
    // It uses ViewBinding to safely access the views from list_item_landmark.xml
    inner class LandmarkViewHolder(private val binding: ListItemLandmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(landmark: Landmark) {
            binding.landmarkName.text = landmark.name
            binding.landmarkLocation.text = landmark.location
            binding.root.setOnClickListener { onClick(landmark) }
        }
    }

    // Called by RecyclerView to create a new ViewHolder (when there are no old ones to reuse).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val binding = ListItemLandmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LandmarkViewHolder(binding)
    }

    // Returns the total number of items in the list.
    override fun getItemCount(): Int = landmarks.size

    // Called by RecyclerView to display the data at a specific position.
    // It gets the data item and binds it to the ViewHolder.
    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        holder.bind(landmarks[position])
    }
}

// The Activity for displaying the list of landmarks.
class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val landmarks = createSampleData()

        // Set up the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = LandmarkAdapter(landmarks) { landmark ->
            // This lambda is executed when an item is clicked.
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("LANDMARK_EXTRA", landmark)
            startActivity(intent)
        }
    }

    private fun createSampleData(): List<Landmark> {
        return listOf(
            Landmark("Eiffel Tower", "Paris, France", "A wrought-iron lattice tower on the Champ de Mars."),
            Landmark("Colosseum", "Rome, Italy", "An oval amphitheatre in the centre of the city of Rome."),
            Landmark("Statue of Liberty", "New York, USA", "A colossal neoclassical sculpture on Liberty Island."),
            Landmark("Machu Picchu", "Cusco Region, Peru", "An Incan citadel set high in the Andes Mountains."),
            Landmark("Great Wall of China", "China", "A series of fortifications that were built across the historical northern borders of ancient Chinese states.")
        )
    }
}

// The Activity for displaying the details of a single landmark.
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the landmark data passed from ListActivity
        val landmark = intent.getSerializableExtra("LANDMARK_EXTRA") as? Landmark

        if (landmark != null) {
            supportActionBar?.title = landmark.name // Set the title of the action bar
            binding.detailLocation.text = landmark.location
            binding.detailDescription.text = landmark.description
        } else {
            // Handle the case where the data is missing
            binding.detailDescription.text = "Landmark data not found."
        }
    }
}
