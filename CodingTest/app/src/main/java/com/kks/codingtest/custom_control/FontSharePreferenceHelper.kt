package com.kks.codingtest.custom_control

import android.content.Context
import android.content.SharedPreferences

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class FontSharePreferenceHelper(context: Context) {

    companion object {
        private val FONT_KEY = "font_name"
        private val LANGUAGE_KEY = "language_name"
    }

    private var sharedPreference: SharedPreferences? = null

    init {
        val SHARE_PREFRENCE = "jcgvandroid.font"
        sharedPreference = context.getSharedPreferences(SHARE_PREFRENCE, Context.MODE_PRIVATE)
    }

    //Zg or MM3
    fun isFontSetup(): Boolean {
        return sharedPreference!!.contains(FONT_KEY)
    }

    fun getFont(): String? {
        return sharedPreference!!.getString(FONT_KEY, "")
    }

    fun setFont(language: String?) {
        val editor = sharedPreference!!.edit()
        editor.putString(FONT_KEY, language)
        editor.apply()
    }

    fun getLanguage(): String? {
        return sharedPreference!!.getString(LANGUAGE_KEY, "")
    }

    fun setLanguage(language: String?) {
        val editor = sharedPreference!!.edit()
        editor.putString(LANGUAGE_KEY, language)
        editor.apply()
    }

    fun isLanguageFontSetup(): Boolean {
        return sharedPreference!!.contains(FONT_KEY) && sharedPreference!!.contains(LANGUAGE_KEY)
    }
}