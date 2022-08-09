package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()
    }

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
//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner, object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    retryGame()
//                }
//            })
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


    private fun parsArgs() {
        val args = requireArguments()
        if (!args.containsKey(KEY_GAME_RESULT)) {
            throw RuntimeException("gameResult is absent")
        }
        args.getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
//        requireActivity().supportFragmentManager
//            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        const val KEY_GAME_RESULT = "game_result"

        fun getInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}