package com.kks.codingtest.custom_control

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class MyanEditText
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        hint = MyanTextProcessor.processText(getContext(), (hint ?: "-").toString())
    }

    fun getMyanmarText() : String {
        return if (MyanmarZawgyiConverter.isZawgyiEncoded(text.toString())) {
            Rabbit.zg2uni(text.toString())
        } else {
            text.toString()
        }
    }

    fun setMyanmarText(text : String?) {
        if (!TextUtils.isEmpty(text)) {
            setText(MyanTextProcessor.processText(context, text ?: "-"))
        }
    }
}