package com.aditprayogo.gamezin.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.LoaderState
import com.aditprayogo.core.utils.setGone
import com.aditprayogo.core.utils.setVisible
import com.aditprayogo.gamezin.databinding.FragmentSearchBinding
import com.aditprayogo.gamezin.game.GameAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val gameAdapter: GameAdapter by lazy { GameAdapter() }

    private val searchViewModel by viewModels<SearchViewModel>()

    private val gameDataEntity = mutableListOf<GameDataEntity>()

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
        searchGame()
    }

    private fun searchGame() {
        with(binding) {
            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            gameDataEntity.clear()
                            searchViewModel.searchGames(it)
                            svSearch.clearFocus()
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            with(rvGame) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = gameAdapter
            }
        }
    }

    private fun initObservers() {
        with(searchViewModel) {
            state.observe(viewLifecycleOwner, {
                handleState(it)
            })
            resultGameApi.observe(viewLifecycleOwner, {
                handleResultGameApi(it)
            })
        }
    }

    private fun handleResultGameApi(it: List<GameDataEntity>) {
        gameDataEntity.clear()
        gameDataEntity.addAll(it)
        gameAdapter.setData(gameDataEntity)
    }

    private fun handleState(it: LoaderState) {
        with(binding) {
            if (it is LoaderState.ShowLoading) {
                baseLoading.root.setVisible()
                rvGame.setGone()
            } else {
                baseLoading.root.setGone()
                rvGame.setVisible()
            }
        }

    }

}