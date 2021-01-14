package com.kks.codingtest.custom_control

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.kks.codingtest.R

/**
 *Created by YaminKhaing on 1/7/2021.

 **/
class MyanProgressDialog(context: Context) {

    private val dialog : Dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.progress_dialog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showDialog() {
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }
}