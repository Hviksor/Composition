package com.example.composition.presentation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.required_score), count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.score_answers), count
    )
}

@BindingAdapter("scoreAnswersColor")
fun bindScoreAnswersColor(textView: TextView, state: Boolean) {
    textView.setTextColor(getColorForState(state, textView.context))
}

@BindingAdapter("scorePercentage")
fun bindingScorePercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.score_percentage),
        count
    )
}

@BindingAdapter("scorePercentageColor")
fun bindScorePercentageColor(textView: TextView, state: Boolean) {
    textView.setTextColor(getColorForState(state, textView.context))
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, state: Boolean) {
    imageView.setImageResource(getResultImg(state))
}


private fun getResultImg(state: Boolean): Int {
    return if (state) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_smile_2
    }
}

private fun getColorForState(state: Boolean?, context: Context): Int {
    val colorId = if (state == true) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorId)
}
















