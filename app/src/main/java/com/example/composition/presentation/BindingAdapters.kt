package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

interface OnOptionClickListeners {
    fun onOptionClick(option: Int)
}

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

@BindingAdapter("resultGreetings")
fun bindResultGreetings(textView: TextView, state: Boolean) {
    textView.text =  if (state) {
        textView.context.resources.getString(R.string.result_greetings_positive)

    } else {
        textView.context.resources.getString(R.string.result_greetings_negative)
    }
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

//__________________GameFragment_______________________________


@BindingAdapter("progressBarProgressPercent")
fun bindProgressBarProgressPercent(progressBar: ProgressBar, percent: Int) {
    progressBar.progress = percent
}

@BindingAdapter("progressBarColor")
fun bindProgressBarColor(progressBar: ProgressBar, state: Boolean) {
    progressBar.progressTintList = ColorStateList.valueOf(
        getColorForState(state, progressBar.context)
    )
}

@BindingAdapter("answersProgressColor")
fun bindAnswersProgressColor(textView: TextView, state: Boolean) {
    textView.setTextColor(
        getColorForState(state, textView.context)
    )
}

@BindingAdapter("numberAsText")
fun bitNumbersAsText(textView: TextView, number: Number) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListeners) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getResultImg(state: Boolean): Int {
    return if (state) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_smile_2
    }
}

private fun getColorForState(state: Boolean, context: Context): Int {
    val colorId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorId)
}
















