package com.pemrogamanmobile.myfavoritegames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pemrogamanmobile.myfavoritegames.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val affirmation = args.affirmation
        with(binding) {
            detailImageView.setImageResource(affirmation.imageResourceId)
            detailTitleText.text  = getString(affirmation.titleResourceId)
            detailYearText.text   = getString(affirmation.yearResourceId)
            detailDescText.text   = getString(affirmation.detailResourceId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}