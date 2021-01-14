package com.kks.codingtest.custom_control

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.kks.codingtest.R

/**
 *Created by YaminKhaing on 1/8/2021.

 **/
//class CustomDialog : Dialog {
//
//    private var mContext: Context? = null
//    private var view: View? = null
//    private var alertTitle: MyanBoldTextView? = null
//    private var positiveBtn: LinearLayout? = null
//    private var positiveText: MyanTextView? = null
//    private var negativeBtn: LinearLayout? = null
//    private var negativeText: MyanTextView? = null
//    private var ivImage: ImageView? = null
//
//    constructor(context: Context) : super(context, R.style.MyDialogTheme) {
//        this.mContext = context
//        initialize(context)
//    }
//
//    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}
//
//    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}
//
//    private fun initialize(context: Context) {
//        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        view = li.inflate(R.layout.order_success_alert, null)
//        ivImage = view!!.findViewById(R.id.iv_dialog)
//        alertTitle = view!!.findViewById(R.id.alert_title)
//        positiveBtn = view!!.findViewById(R.id.llOk)
//        positiveText = view!!.findViewById(R.id.tv_positive)
//        negativeBtn = view!!.findViewById(R.id.llCancle)
//        negativeText = view!!.findViewById(R.id.tv_negative)
//        setCancelable(true)
//        setContentView(view!!)
//        setCanceledOnTouchOutside(true)
//    }
//
//    fun setAlertTitle(title: String?) {
//        alertTitle!!.setMyanmarText(title)
//    }
//
//    fun setIcon(url: Drawable?) {
//        Glide.with(context)
//                .asDrawable()
//                .load(url)
//                .into(ivImage!!)
//    }
//
//    fun setPositiveButton(name: String?, onClickListener: View.OnClickListener?) {
//        positiveText!!.setMyanmarText(name)
//        positiveBtn!!.setOnClickListener(onClickListener)
//    }
//
//    fun setNegativeButton(name: String?, onClickListener: View.OnClickListener?) {
//        negativeText!!.setMyanmarText(name)
//        negativeBtn!!.setOnClickListener(onClickListener)
//    }
//}

/*
  <style name="MyDialogTheme">
        <item name="android:windowFrame">@null</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowBackground">@color/colorTransparent</item>
        <item name="android:colorBackgroundCacheHint">@null</item>
    </style>
 */