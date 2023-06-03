package com.example.tvshowsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tvshowsinfo.data.TvShowDatabase
import com.example.tvshowsinfo.databinding.FragmentTvShowDetailBinding
import com.example.tvshowsinfo.repository.TvShowRepository
import com.example.tvshowsinfo.viewmodel.TvShowViewModel
import com.example.tvshowsinfo.viewmodel.TvShowViewModelFactory

class TvShowDetailFragment : Fragment() {
    private lateinit var binding: FragmentTvShowDetailBinding
    private val viewModel: TvShowViewModel by viewModels {
        TvShowViewModelFactory(TvShowRepository(TvShowDatabase.getDatabase(requireContext()).tvShowDao()))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowId = arguments?.getInt("tvShowId")

        // use ViewModel to fetch the TV show details by its id
        viewModel.getTvShow(tvShowId!!).observe(viewLifecycleOwner) { tvShow ->
        binding.tvShowName.text = tvShow.name
            binding.tvShowGenre.text = tvShow.genre
            binding.tvShowSynopsis.text = tvShow.synopsis

            // Load the image
            Glide.with(this)
                .load(tvShow.imageUrl)
                .centerCrop()
                .into(binding.tvShowImage)
        }
    }
}
