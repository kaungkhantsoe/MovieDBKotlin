package com.kks.codingtest.custom_control

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class MyanBoldTextView
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        applyCustomFont(context)
        text = MyanTextProcessor.processText(getContext(), (text ?: "-").toString())
    }

    fun setMyanmarText(text: String?) {
        applyCustomFont(context)
        post {
            if (text != null) {
                if (text.isNotEmpty()) {
                    val myanText = MyanTextProcessor.processText(context, text)
                    setText(myanText)
                } else {
                    setText("-")
                }
            } else {
                setText("-")
            }
        }
    }

    fun setMyanmarToastText(text: String?) {
        val myanText = MyanTextProcessor.processText(context, text ?: "-")
        setText(myanText)
    }

    private fun applyCustomFont(context: Context) {
        val customFont: Typeface? = FontCache.getTypeface("Geomanist_Medium.ttf", context)
        typeface = customFont
    }
}