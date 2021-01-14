package com.kks.codingtest.custom_control

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class MyanCheckBox
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatCheckBox(context, attrs, defStyleAttr) {

    init {
        text = MyanTextProcessor.processText(getContext(), (text ?: "-").toString())
    }

    fun getMyanmarText() : String {
        return if (MyanmarZawgyiConverter.isZawgyiEncoded(text.toString())) {
            Rabbit.zg2uni(text.toString())
        } else {
            text.toString()
        }
    }

    fun setMyanmarText(text : String?) {
        setText(MyanTextProcessor.processText(context, text ?: "-"))
    }

}