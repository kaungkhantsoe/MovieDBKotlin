package com.kks.codingtest.common

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.kks.codingtest.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
abstract class BaseActivity(@LayoutRes val layout: Int) : DaggerAppCompatActivity() {

//    lateinit var toolbar_text : TextView
//    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setUpContents(savedInstanceState)

//        toolbar = findViewById(R.id.toolbar)
//        toolbar_text = findViewById(R.id.toolbar_text)
    }

    protected fun setupToolbar(isChild: Boolean) {
        setSupportActionBar(toolbar)
        if (toolbar != null)
            setSupportActionBar(toolbar);

        if (isChild) {
            if (supportActionBar != null) {

                /*final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
                upArrow.setColorFilter(getResources().getColor(R.color.colorTextColorPrimary), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(upArrow);*/
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                toolbar.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }

    protected fun setupToolbarText(text: String?) {
        //getSupportActionBar().setTitle(text);
        toolbar_text.text = text
    }

    protected fun setupToolbarBgColor(color: String?) {
        toolbar!!.setBackgroundColor(Color.parseColor(color))
    }

    protected fun setupToolbarTextColor(color: String?) {
//        toolbar.setTitleTextColor(Color.parseColor(color));
        toolbar_text.setTextColor(Color.parseColor(color))
    }

    protected abstract fun setUpContents(savedInstanceState: Bundle?)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
