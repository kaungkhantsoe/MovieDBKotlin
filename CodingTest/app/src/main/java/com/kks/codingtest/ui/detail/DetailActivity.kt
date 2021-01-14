package com.kks.codingtest.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.kks.codingtest.R
import com.kks.codingtest.common.BaseActivity
import com.kks.codingtest.data.models.ResultModel
import com.kks.codingtest.util.AppConstants.BASE_IMG_URL
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/5/21.
 **/
class DetailActivity : BaseActivity(R.layout.activity_detail) {

    companion object {
        val IE_RESULT = "IE_RESULT"
        fun start(context: Context, resultModel: ResultModel) {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(IE_RESULT,resultModel)
            context.startActivity(intent)
        }
    }

    lateinit var detailModel: ResultModel

    @Inject
    lateinit var requestManager: RequestManager

    override fun setUpContents(savedInstanceState: Bundle?) {

        setupToolbar(true)

        detailModel = intent.getSerializableExtra(IE_RESULT) as ResultModel

        requestManager.load(BASE_IMG_URL + detailModel.posterPath)
            .into(detailImageView)

        titleTv.setMyanmarText(detailModel.title)
        descriptionTv.setMyanmarText(detailModel.overview)
    }
}