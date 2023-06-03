package com.example.tvshowsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsinfo.databinding.FragmentLandingPageBinding
import com.example.tvshowsinfo.domain.TvShow

class LandingPageFragment : Fragment() {

    // View binding
    private var _binding: FragmentLandingPageBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val tvShowAdapter by lazy { TvShowAdapter(::navigateToTvShowDetail) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadTvShows()

        navController = Navigation.findNavController(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvTvShows.layoutManager = LinearLayoutManager(context)
        binding.rvTvShows.adapter = tvShowAdapter
    }

    private fun loadTvShows() {
        tvShowAdapter.tvShows = MockDataSource.tvShows
    }

    private fun navigateToTvShowDetail(tvShow: TvShow) {
        val action = LandingPageFragmentDirections.actionLandingPageFragmentToTvShowDetailFragment(tvShow.id)
        navController.navigate(action)
    }
}
