package com.example.tvshowsinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tvshowsinfo.data.TvShowDatabase
import com.example.tvshowsinfo.repository.TvShowRepository
import com.example.tvshowsinfo.viewmodel.TvShowViewModel
import com.example.tvshowsinfo.viewmodel.TvShowViewModelFactory
import com.example.tvshowsinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the NavHostFragment using its ID specified in activity_main.xml
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Get the NavController
        navController = navHostFragment.navController

        // Set up the ActionBar with the NavController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Initialize your ViewModel
        val tvShowDao = TvShowDatabase.getDatabase(this).tvShowDao()
        val repository = TvShowRepository(tvShowDao)
        tvShowViewModel = ViewModelProvider(this, TvShowViewModelFactory(repository)).get(TvShowViewModel::class.java)

        // Check if database is empty and if so, insert mock data into the database
        tvShowViewModel.allTvShows.observe(this) { tvShows ->
            if (tvShows.isEmpty()) {
                for (tvShow in MockDataSource.tvShows) {
                    tvShowViewModel.insert(tvShow)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
