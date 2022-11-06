package com.aditprayogo.gamezin.game

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.utils.LoaderState
import com.aditprayogo.gamezin.R
import com.aditprayogo.gamezin.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val binding: FragmentGameBinding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }

    private val gameAdapter: GameAdapter by lazy {
        GameAdapter()
    }

    private val gameViewModel by viewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        initSwipe()
    }

    private fun initSwipe() {
        binding.apply {
            swipeView.setOnRefreshListener {
                gameViewModel.getAllGames()
                swipeView.isRefreshing = false
            }
        }
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
            state.observe(viewLifecycleOwner) {
                handleState(it)
            }
            resultGameApi.observe(viewLifecycleOwner) {
                handleResultGameApi(it)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            networkError.observe(viewLifecycleOwner) {
                Toast.makeText(context, getString(R.string.text_network_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleResultGameApi(data: List<GameData>) {
        gameAdapter.submitList(data)
    }

    private fun handleState(it: LoaderState) {
        with(binding) {
            if (it is LoaderState.ShowLoading) {
                baseLoading.root.visibility = View.VISIBLE
                rvGame.visibility = View.INVISIBLE
            } else {
                baseLoading.root.visibility = View.INVISIBLE
                rvGame.visibility = View.VISIBLE
            }
        }
    }


}