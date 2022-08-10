package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    private val args by navArgs<GameFinishedFragmentArgs>()

    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameResult = args.gameResult
        setOnClickListeners()
        setBinding()
    }

    private fun setBinding() {
        binding.emojiResult.setImageResource(getResultImg(gameResult.winner))
        binding.tvRequiredAnswers.text = String.format(
            requireActivity().resources.getString(R.string.required_score),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvRequiredAnswers.text = String.format(
            requireActivity().resources.getString(R.string.required_score),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            requireActivity().resources.getString(R.string.required_percentage),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            requireActivity().resources.getString(R.string.score_answers),
            gameResult.countOfRightAnswers
        )
        binding.tvScoreAnswers.setTextColor(getColorFromState(gameResult.enoughCount))
        binding.tvScorePercentage.setTextColor(getColorFromState(gameResult.enoughPercent))
        binding.tvScorePercentage.text = String.format(
            requireActivity().resources.getString(R.string.score_percentage),
            gameResult.percentRightAnswer
        )
    }

    private fun getColorFromState(state: Boolean?): Int {
        val colorId = if (state == true) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorId)
    }

    private fun setOnClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun getResultImg(state: Boolean): Int {
        return if (state) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_smile_2
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}