package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings,
    val enoughCount: Boolean?,
    val enoughPercent: Boolean?,
    val percentRightAnswer: Int?
) : Parcelable {
}
