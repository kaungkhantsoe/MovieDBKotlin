package com.kks.codingtest.custom_control

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class MyanRadioButton
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatRadioButton(context, attrs, defStyleAttr){

    init {
        setMyanmarText(text.toString())
    }

    fun getMyanmarText(): String {
        return if (MyanmarZawgyiConverter.isZawgyiEncoded(text.toString())) {
            Rabbit.zg2uni(text.toString())
        } else {
            text.toString()
        }
    }

    fun setMyanmarText(text: String?) {
        applyCustomFont(context)
        setText(MyanTextProcessor.processText(context, text ?: "-"))
    }

    private fun applyCustomFont(context: Context) {
        val customFont: Typeface? = FontCache.getTypeface("Geomanist_Regular.otf", context)!!
        typeface = customFont
    }
}
