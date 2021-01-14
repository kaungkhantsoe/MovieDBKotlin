package com.kks.codingtest.custom_control

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatButton

/**
 *Created by YaminKhaing on 1/8/2021.
 **/
class MyanButton
@JvmOverloads
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = R.attr.buttonStyle): AppCompatButton(context,attrs,defStyleAttr)  {

    init {
        setMyanmarText(text.toString())
    }

    fun setMyanmarText(text: String?) {
        applyCustomFont(context)
        setText(MyanTextProcessor.processText(context, text ?: "-"))
    }

    private fun applyCustomFont(context: Context) {

//        val customFont: Typeface? = FontCache.getTypeface("Geomanist_Regular.otf", context)
//        typeface = customFont
    }
}