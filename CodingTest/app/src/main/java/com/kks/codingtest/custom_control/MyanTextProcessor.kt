package com.kks.codingtest.custom_control

import android.content.Context

/**
 *Created by YaminKhaing on 1/8/2021.

 **/

class MyanTextProcessor {

    companion object {
        fun processText(context: Context, original_text: String): String {

            var originalText = original_text

            when (AndroidCommonSetup.getInstance().getZgOrMM3()) {
                "zg" -> originalText = Rabbit.uni2zg(original_text)
                else -> {
                }
            }
            return originalText
        }
    }
}