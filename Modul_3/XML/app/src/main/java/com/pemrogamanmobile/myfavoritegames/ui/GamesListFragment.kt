package com.pemrogamanmobile.myfavoritegames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pemrogamanmobile.myfavoritegames.data.DataSource
import com.pemrogamanmobile.myfavoritegames.databinding.FragmentGamesListBinding

class GamesListFragment : Fragment() {
    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?
    ) = FragmentGamesListBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val games = DataSource.loadGames()
        binding.recyclerView.adapter = GamesAdapter(games) { game ->
            val action = GamesListFragmentDirections
                .actionListToDetail(game)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}