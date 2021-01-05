package com.kks.codingtest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kks.codingtest.R
import com.kks.codingtest.common.BaseAdapter
import com.kks.codingtest.data.models.Result
import com.kks.codingtest.util.BASE_IMG_URL
import timber.log.Timber

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class MainAdapter(
    val requestManager: RequestManager,
    val mainListener: MainListener) : BaseAdapter() {

    override fun onCreateCustomViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent?.getContext())
            .inflate(R.layout.item_movie, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ChildViewHolder).bindPost(
            itemsList[position] as Result
        )
    }

    override fun onCreateCustomHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent?.getContext())
            .inflate(R.layout.item_dummy, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindCustomHeaderViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    inner class HeaderViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class ChildViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.titleTv)

        fun bindPost(model: Result) {
            Timber.d("Link " + BASE_IMG_URL + model.posterPath)
            requestManager.load(BASE_IMG_URL + model.posterPath)
                .into(imageView)
            title.text = model.title

            itemView.setOnClickListener {
                mainListener.onClickMovie(model)
            }
        }

    }
}