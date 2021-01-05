package com.kks.codingtest.ui.main

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
import com.kks.codingtest.data.models.Result
import com.kks.codingtest.ui.detail.DetailActivity
import com.kks.codingtest.util.SmartScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class MainActivity :
    BaseActivity(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener,
    SmartScrollListener.OnSmartScrollListener,
    MainListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var requestManager: RequestManager

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    lateinit var adapter: MainAdapter

    init {
        System.loadLibrary("libnative-lib");
    }

    override fun setUpContents(savedInstanceState: Bundle?) {

        viewModel.apiKey = getApiKey().toString()

        swipeRefresh.setOnRefreshListener(this)

        setupRecycler()

        observeData()

        viewModel.getData(1)

    }

    fun setupRecycler() {
        adapter = MainAdapter(requestManager, this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = this.adapter
        recyclerView.addOnScrollListener(SmartScrollListener(this@MainActivity))
    }

    private fun observeData() {
        viewModel.mainRepository.liveData.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Result>> -> {
                    displayProgressBar(false)
                    swipeRefresh.isRefreshing = false
                    for (model in dataState.data)
                        adapter.add(model)
                }
                is DataState.Error -> {
                    viewModel.page = viewModel.page - 1
                    swipeRefresh.isRefreshing = false
                    displayProgressBar(false)
                    Toast.makeText(this@MainActivity, dataState.exception, Toast.LENGTH_LONG).show()
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

    external fun getApiKey(): String?

    override fun onRefresh() {
        adapter.clear()
        viewModel.getData(1)
    }

    override fun onListEndReach() {
        viewModel.getData(viewModel.page + 1)
    }

    override fun onClickMovie(result: Result) {
        DetailActivity.start(this@MainActivity, result)
    }

}