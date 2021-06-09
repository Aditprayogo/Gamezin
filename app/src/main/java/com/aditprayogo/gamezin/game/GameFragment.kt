package com.aditprayogo.gamezin.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.LoaderState
import com.aditprayogo.gamezin.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val binding : FragmentGameBinding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }

    private val gameAdapter : GameAdapter by lazy {
        GameAdapter()
    }

    private var gameDataEntity = mutableListOf<GameDataEntity>()

    private val gameViewModel by viewModels<GameViewModel>()

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
            rvGame.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = gameAdapter
            }
        }
    }

    private fun initObservers() {
        with(gameViewModel) {
            state.observe(viewLifecycleOwner, {
                handleState(it)
            })
            resultGameApi.observe(viewLifecycleOwner, {
                handleResultGameApi(it)
            })
        }
    }

    private fun handleResultGameApi(data: List<GameDataEntity>) {
        gameDataEntity.clear()
        gameDataEntity.addAll(data)
        gameAdapter.setData(gameDataEntity)
    }

    private fun handleState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            // TODO: 09/06/21
        } else {
            // TODO: 09/06/21
        }
    }

}