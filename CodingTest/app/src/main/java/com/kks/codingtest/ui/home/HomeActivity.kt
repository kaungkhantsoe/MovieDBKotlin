package com.kks.codingtest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kks.codingtest.R
import com.kks.codingtest.common.BaseActivity
import com.kks.codingtest.ui.main.MainActivity
import com.kks.codingtest.ui.main_coroutine.MainCoroutineActivity

/**
 * Created by kaungkhantsoe on 1/14/21.
 **/
class HomeActivity : BaseActivity(R.layout.activity_home) {

    override fun setUpContents(savedInstanceState: Bundle?) {

    }

    fun onClickUsingCoroutine(view: View) {
        startActivity(Intent(this@HomeActivity, MainCoroutineActivity::class.java))
    }

    fun onClickUsingRx(view: View) {
        startActivity(Intent(this@HomeActivity, MainActivity::class.java))
    }
}