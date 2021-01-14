package com.kks.codingtest.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

/**
 * Created by maykyawtthu on 1/6/2021
 **/

abstract class BaseFragment : DaggerFragment() {

    var mContext: Context? = null

    private var someStateValue = 0
    private val SOME_VALUE_KEY = "someValueToSave"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY)
            // Do something with value if needed
        }
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = view.context
        setUpContents(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected abstract fun setUpContents(savedInstanceState: Bundle?)

}