package com.kks.codingtest.custom_control

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView

/**
 *Created by YaminKhaing on 1/8/2021.

 **/

const val ZG_FONT = "zg"
const val MM3_FONT = "mm3"

class AndroidCommonSetup {

    private var sharePreferenceHelper: FontSharePreferenceHelper? = null
    private var fontChecked = false

    companion object {
        private val ourInstance = AndroidCommonSetup()

        fun getInstance() : AndroidCommonSetup {
            return ourInstance
        }
    }

    fun init(context: Context) {
        sharePreferenceHelper = FontSharePreferenceHelper(context)

        if (fontChecked) {
            return
        }

        //Credit: Myat Min Soe MDetect.
        val textView = TextView(context, null)
        textView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        textView.text = "\u1000"
        textView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val length1 = textView.measuredWidth

        textView.text = "\u1000\u1039\u1000"
        textView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val length2 = textView.measuredWidth

        fontChecked = if (length1 == length2) {
            //mm3
            changeFont(MM3_FONT)
            true
        } else {
            //zg
            changeFont(ZG_FONT)
            true
        }
    }

    fun getZgOrMM3() : String {
        return sharePreferenceHelper!!.getFont()!!
    }

    //zg or mm3
    fun changeFont(font: String) {
        sharePreferenceHelper!!.setFont(font)
    }

    fun getSelectedLanguage() : String {
        return sharePreferenceHelper!!.getLanguage()!!
    }

    fun changeLanguage(language: String) {
        sharePreferenceHelper!!.setLanguage(language)
    }

}
