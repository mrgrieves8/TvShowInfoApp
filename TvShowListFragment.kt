package com.example.tvshowsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.tvshowsinfo.data.TvShowDatabase
import com.example.tvshowsinfo.databinding.FragmentTvShowListBinding
import com.example.tvshowsinfo.repository.TvShowRepository
import com.example.tvshowsinfo.viewmodel.TvShowViewModel
import com.example.tvshowsinfo.viewmodel.TvShowViewModelFactory

class TvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding
    private val viewModel: TvShowViewModel by viewModels {
        TvShowViewModelFactory(TvShowRepository(TvShowDatabase.getDatabase(requireContext()).tvShowDao()))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allTvShows.observe(viewLifecycleOwner, { tvShows ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, tvShows.map { it.name })
            binding.listView.adapter = adapter
        })

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val tvShow = viewModel.allTvShows.value?.get(position)
            tvShow?.let {
                findNavController().navigate(R.id.action_tvShowListFragment_to_tvShowDetailFragment, bundleOf("tvShowId" to it.id))
            }
        }
    }
}
