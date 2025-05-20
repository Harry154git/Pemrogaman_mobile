package com.pemrogamanmobile.myfavoritegames.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.pemrogamanmobile.myfavoritegames.GamesViewModel
import com.pemrogamanmobile.myfavoritegames.GamesViewModelFactory
import com.pemrogamanmobile.myfavoritegames.databinding.FragmentGamesListBinding
import kotlinx.coroutines.launch
import timber.log.Timber

class GamesListFragment : Fragment() {
    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GamesViewModel by viewModels {
        GamesViewModelFactory("All") // string pakai All
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentGamesListBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = GamesAdapter(
            onViewClicked = { game ->
                val title = requireContext().getString(game.titleResourceId)
                Timber.d("Launching external app with title: $title")

                // Intent ke aplikasi lain menggunakan URI
                val url = requireContext().getString(game.steamLinkResourceId)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                if (intent.resolveActivity(requireContext().packageManager) != null) {
                    startActivity(intent)
                } else {
                    Timber.e("No application can handle this intent.")
                }
            },
            onDetailClicked = { game ->
                val title = requireContext().getString(game.titleResourceId)
                Timber.d("Navigating to DetailFragment for game: $title")
                viewModel.onGameClicked(game)
                val action = GamesListFragmentDirections.actionListToDetail(game)
                findNavController().navigate(action)
            }
        )
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gamesState.collect { games ->
                    Timber.d("Games list updated: $games")
                    adapter.submitList(games)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
