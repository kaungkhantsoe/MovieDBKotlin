package com.kks.codingtest.custom_control

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.kks.codingtest.R

/**
 *Created by YaminKhaing on 1/7/2021.

 **/
class MyanToast {

    fun makeShortText(context: Context, text : String) {
        val inflater = LayoutInflater.from(context)
        val layout : View = inflater.inflate(R.layout.toast_layout, null)

        val textView: MyanTextView = layout.findViewById(R.id.txtToastMessage)
        textView.setMyanmarToastText(text)

        Toast.makeText(context, textView.text, Toast.LENGTH_SHORT).show()
    }

    fun makeLongText(context : Context, text: String) {
        val inflater = LayoutInflater.from(context)

        val layout = inflater.inflate(R.layout.toast_layout, null)

        val textView : MyanTextView = layout.findViewById(R.id.txtToastMessage)
        textView.setMyanmarToastText(text)

        Toast.makeText(context, textView.text, Toast.LENGTH_LONG).show()
    }
}
