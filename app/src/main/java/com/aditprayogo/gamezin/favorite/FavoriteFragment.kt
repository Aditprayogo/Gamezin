package com.aditprayogo.gamezin.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.gamezin.R
import com.aditprayogo.gamezin.databinding.FragmentFavoriteBinding
import com.aditprayogo.gamezin.game.GameAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        with(favoriteViewModel) {
            getAllFavoriteGames().observe(viewLifecycleOwner, {
                handleFavoriteGame(it)
            })
        }
    }

    private fun handleFavoriteGame(it: List<GameFavoriteData>) {
        favoriteGames.clear()
        favoriteGames.addAll(it)
        favoriteAdapter.setFavoriteData(favoriteGames)
    }

}