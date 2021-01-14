package com.kks.codingtest.custom_control

import android.content.Context
import android.graphics.Typeface
import java.util.*

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
class FontCache {

    companion object {
        private val fontCache = HashMap<String, Typeface?>()

        fun getTypeface(fontname: String, context: Context): Typeface? {

            var typeface = fontCache.get(fontname)

            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(context.assets, fontname)
                } catch (e: Exception) {
                    return null
                }
                fontCache.put(fontname, typeface)
            }
            return typeface
        }
    }
}