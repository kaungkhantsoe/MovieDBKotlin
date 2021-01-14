package com.kks.codingtest.ui.main_coroutine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.RequestManager
import com.kks.codingtest.R
import com.kks.codingtest.common.BaseActivity
import com.kks.codingtest.common.DataState
import com.kks.codingtest.data.models.ResultModel
import com.kks.codingtest.ui.detail.DetailActivity
import com.kks.codingtest.ui.main.MainAdapter
import com.kks.codingtest.ui.main.MainListener
import com.kks.codingtest.ui.main.MainViewModel
import com.kks.codingtest.util.SharedKeys
import com.kks.codingtest.util.SmartScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/13/21.
 **/
class MainCoroutineActivity :
    BaseActivity(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener,
    SmartScrollListener.OnSmartScrollListener,
    MainListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var sharedKeys: SharedKeys

    private val viewModel: MainCoroutineViewModel by viewModels {
        viewModelFactory
    }

    lateinit var adapter: MainAdapter

    override fun setUpContents(savedInstanceState: Bundle?) {

        setupToolbar(true)
        setupToolbarText("Using Coroutine")

        viewModel.apiKey = sharedKeys.getApiKey().toString()

        swipeRefresh.setOnRefreshListener(this)

        setupRecycler()

        observeData()

        if (viewModel.page == 0) {
            viewModel.getMovieListFrom(1)
        }
    }

    fun setupRecycler() {
        adapter = MainAdapter(requestManager, this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this@MainCoroutineActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = this.adapter
        recyclerView.addOnScrollListener(SmartScrollListener(this@MainCoroutineActivity))
    }

    private fun observeData() {
        viewModel.mainRepository.liveData.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<ResultModel>> -> {
                    displayProgressBar(false)
                    swipeRefresh.isRefreshing = false

                    for (model in dataState.data)
                        adapter.add(model)
                }
                is DataState.Error -> {
                    viewModel.page = viewModel.page - 1
                    swipeRefresh.isRefreshing = false
                    displayProgressBar(false)
                    Timber.e(dataState.exception)
                    Toast.makeText(
                        this@MainCoroutineActivity,
                        dataState.exception.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onRefresh() {
        adapter.clear()
        viewModel.getMovieListFrom(1)
    }

    override fun onListEndReach() {
        viewModel.getMovieListFrom(viewModel.page + 1)
    }

    override fun onClickMovie(resultModel: ResultModel) {
        DetailActivity.start(this@MainCoroutineActivity, resultModel)
    }

}