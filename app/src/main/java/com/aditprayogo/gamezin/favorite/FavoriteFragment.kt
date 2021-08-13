package com.aditprayogo.gamezin.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.gamezin.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    private val favoriteAdapter by lazy {
        FavoriteAdapter()
    }

    private val binding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    private val favoriteGames = mutableListOf<GameFavoriteData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvGame.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvGame.adapter = favoriteAdapter
        }
    }

    private fun initObservers() {
        favoriteViewModel.getAllFavoriteGames().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                favoriteAdapter.submitData(it)
            }
        })
    }
}