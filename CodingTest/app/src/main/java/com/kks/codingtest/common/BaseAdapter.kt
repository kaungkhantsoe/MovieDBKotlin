package com.kks.codingtest.common

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kks.codingtest.R

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

abstract class BaseAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemsList = arrayListOf<Pageable>()

    private var recyclerView: RecyclerView? = null

    companion object {
        private val TAG = BaseAdapter::class.java.simpleName
        private const val VIEW_LOADING = 0
        private const val VIEW_RETRY = 1
        private const val VIEW_ITEM = 2
        private const val VIEW_HEADER = 3
        private const val VIEW_EMPTY = 4
        const val VIEW_LIST_END = 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_LOADING -> {
                ProgressHolder(prepareLoadingView(parent.context))
            }
            VIEW_RETRY -> {
                val retryFooter = itemsList[itemsList.size - 1] as RetryFooter
                val view = LayoutInflater.from(parent.context)
                        .inflate(retryFooter.retryLayoutId, parent, false)
                RetryHolder(view, retryFooter.retryActionTriggerViewId)
            }
            VIEW_HEADER -> {
                onCreateCustomHeaderViewHolder(parent, viewType)
            }

            VIEW_EMPTY -> {
                val customView = itemsList[itemsList.size - 1] as EmptyView
                val view = LayoutInflater.from(parent.context)
                        .inflate(customView.customLayoutId, parent, false)
                EmptyViewHolder(view)
            }
            VIEW_LIST_END -> {
                val customView = itemsList[itemsList.size - 1] as ListEndView
                val view = LayoutInflater.from(parent.context)
                        .inflate(customView.customLayoutId, parent, false)
                ListEndHolder(view)
            }
            else -> {
                onCreateCustomViewHolder(parent, viewType)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_LOADING -> {
                (holder as ProgressHolder).progressBar.isIndeterminate = true
            }
            VIEW_RETRY -> {
                val footer = itemsList[position] as RetryFooter
                (holder as RetryHolder).setOnRetryListener(footer.onRetryListener)
            }
            VIEW_HEADER -> {
                onBindCustomHeaderViewHolder(holder, position)
            }
            VIEW_EMPTY -> {
                Log.d(TAG, "onBindViewHolder: Skipping View Binding of EmptyView Item")
            }
            VIEW_LIST_END -> {
                Log.d(TAG, "onBindViewHolder: Skipping View Binding of ListEndView Item")
            }
            else -> {
                onBindCustomViewHolder(holder, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: Pageable = itemsList[position]
        return if (item is ProgressFooter) {
            VIEW_LOADING
        } else if (item is RetryFooter) {
            VIEW_RETRY
        } else if (item is RecyclerHeader<*>) {
            VIEW_HEADER
        } else if (item is EmptyView) {
            VIEW_EMPTY
        } else if (item is ListEndView) {
            VIEW_LIST_END
        } else {
            VIEW_ITEM
        }
    }

    fun startAnimateAllItems(context: Context?, @AnimRes animId: Int) {
        if (itemCount > 0) for (position in 0 until itemCount) {
            if (recyclerView!!.layoutManager is LinearLayoutManager) {
                val animation = AnimationUtils.loadAnimation(context, animId)
                val view = recyclerView!!.layoutManager?.findViewByPosition(position)
                if (view != null) if (getItemViewType(position) == VIEW_ITEM) view.startAnimation(
                    animation
                )
            }
        }
    }

    fun startAnimateItemAtPosition(context: Context?, position: Int, @AnimRes animId: Int) {
        if (position < itemCount) {
            if (recyclerView!!.layoutManager is LinearLayoutManager) {
                val animation = AnimationUtils.loadAnimation(context, animId)
                val view = recyclerView!!.layoutManager?.findViewByPosition(position)
                if (view != null) if (getItemViewType(position) == VIEW_ITEM) view.startAnimation(
                    animation
                )
            }
        }
    }

    fun stopAnimateItemAtPosition(position: Int) {
        if (position < itemCount) {
            if (recyclerView!!.layoutManager is LinearLayoutManager) {
                val view = recyclerView!!.layoutManager?.findViewByPosition(position)
                view?.clearAnimation()
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager = manager
            gridManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    return if (type == VIEW_ITEM) 1 else gridManager.spanCount
                }
            }
        }
    }

    /**
     * Removes the last item if it is either ProgressFooter or RetryFooter
     */
    fun clearFooter() {
        if (itemsList != null && itemsList.isNotEmpty()) {
            val pageable: Pageable = itemsList[itemsList.size - 1]
            if (pageable is RetryFooter || pageable is ProgressFooter || pageable is EmptyView) {
                itemsList.removeAt(itemsList.size - 1)
                notifyItemRemoved(itemsList.size)
            }
        }
    }

    /**
     * Add one pageable data to a specific position in adapter
     *
     * @param data     Item to be inserted
     * @param position Position where to insert
     */
    fun add(data: Pageable, position: Int) {
        if (0 <= position && position > itemsList.size) {
            itemsList.add(position, data)
            notifyItemInserted(position)
        } else {
            throw ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size")
        }
    }

    /**
     * Add one pageable data to last position in adapter
     *
     * @param data Item to be inserted
     */
    fun add(data: Pageable) {
        itemsList.add(data)
        notifyItemInserted(itemsList.size)
    }

    /**
     * Add list of pageable items in adapter
     *
     * @param newItems List of items to be inserted
     */
    fun add(newItems: List<Pageable>) {
        itemsList.addAll(newItems)
        notifyItemRangeInserted(itemsList.size - newItems.size, newItems.size)
    }

    fun update(newData: Pageable, position: Int) {
        if (0 <= position && position < itemsList.size) {
            itemsList[position] = newData
            notifyItemChanged(position)
        } else {
            throw ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size")
        }
    }

    /**
     * Remove one pagable data from a specific position in adapter
     *
     * @param position Position to be removed
     */
    fun remove(position: Int) {
        if (0 <= position && position < itemsList.size) {
            itemsList.removeAt(position)
            notifyItemRemoved(position)
        } else {
            throw ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size")
        }
    }

    /**
     * Remove a range of pagable data from a specific position to a specific position
     *
     * @param startPosition Start position to remove
     * @param endPosition   End position to remove
     */
    fun remove(startPosition: Int, endPosition: Int) {
        val isValidStart = 0 <= startPosition && startPosition < itemsList.size
        val isValidEnd = 0 <= endPosition && endPosition < itemsList.size
        if (isValidStart && isValidEnd) {
            Log.i("BaseRCAdapter", "remove: Removed")
            itemsList.subList(startPosition, endPosition).clear()
            notifyItemRangeRemoved(startPosition, endPosition)
        }
    }

    /**
     * Add HeaderView to the Adapter
     *
     * @param headerData Data to be shown in HeaderView
     */
    fun <T> addHeader(headerData: T) {
        add(RecyclerHeader(headerData))
    }

    /**
     * Add LoadingView to the Adapter
     */
    fun showLoading() {
        add(ProgressFooter())
    }

    /**
     * Add RetryView to the adapter
     *
     * @param retryLayoutId      Layout ID of the retryView
     * @param retryTriggerViewId Id of the retryView element to trigger retry action on click
     * @param retryListener      RetryListener
     */
    fun showRetry(
        @LayoutRes retryLayoutId: Int,
        @IdRes retryTriggerViewId: Int,
        retryListener: OnRetryListener?
    ) {
        add(RetryFooter(retryListener, retryLayoutId, retryTriggerViewId))
    }

    /**
     * Add EmptyView to the Adapter
     */
    fun showEmptyView(customLayout: Int) {
        add(EmptyView(customLayout))
    }

    /**
     * Add EmptyView to the Adapter
     */
    fun showListEndView(customLayout: Int) {
        add(ListEndView(customLayout))
    }

    /**
     * Clear all items in the adapter
     */
    fun clear() {
        itemsList.clear()
        notifyDataSetChanged()
    }

    /**
     * Get isLoading
     */
    fun isLoading(): Boolean {
        return itemCount > 0 && itemsList[itemCount - 1] is ProgressFooter
    }

    fun getHeaderPosition(): Int {
        var position = -1
        for (p in itemsList) {
            if (p is RecyclerHeader<*>) {
                position = itemsList.indexOf(p)
                return position
            }
        }
        return position
    }

//    /**
//     * Get List of Items in the adapter
//     *
//     * @return List of Items in the adapter
//     */
//    @JvmName("getItemsList1")
//    fun getItemsList(): List<Pageable>? {
//        return itemsList
//    }

    fun dummyHeader(context: Context?) : RecyclerView.ViewHolder {
        return DummyHeaderViewHolder(prepareEmptyDummyHeaderView(context))
    }
    private fun prepareLoadingView(context: Context): View {
        val relativeLayout = RelativeLayout(context)
        val relativeLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        relativeLayout.layoutParams = relativeLayoutParams
        val progressBar = ProgressBar(context)
        progressBar.setPadding(5, 5, 5, 5)
        progressBar.tag = 123
        relativeLayout.addView(progressBar)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar.layoutParams = params
        return relativeLayout
    }

    private fun prepareEmptyDummyHeaderView(context: Context?): View {
        val relativeLayout = RelativeLayout(context)
        val relativeLayoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        relativeLayout.layoutParams = relativeLayoutParams
        return relativeLayout
    }

    /*------------------------------------Abstract Methods to be implemented by child classes------------------------------------*/ /*To perform onCreateViewHolder tasks for custom item*/
    protected abstract fun onCreateCustomViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder

    /*To perform onBindViewHolder tasks for custom item*/
    protected abstract fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder?, position: Int)

    /*To perform onCreateViewHolder tasks for HeaderView*/
    protected abstract fun onCreateCustomHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder

    /*To perform onBindViewHolder tasks for Header*/
    protected abstract fun onBindCustomHeaderViewHolder(
        holder: RecyclerView.ViewHolder?,
        position: Int
    )

    /*------------------------------------Required Classes and Interface------------------------------------*/
    interface OnRetryListener {
        fun onRetry()
    }

    class ProgressFooter : Pageable
    class EmptyView(@field:LayoutRes val customLayoutId: Int) : Pageable
    class ListEndView(@field:LayoutRes val customLayoutId: Int) : Pageable
    class RetryFooter(
        val onRetryListener: OnRetryListener?,
        @field:LayoutRes @param:LayoutRes val retryLayoutId: Int,
        @field:IdRes @param:IdRes val retryActionTriggerViewId: Int
    ) : Pageable

    class RecyclerHeader<T>(val headerData: T) : Pageable

    /*------------------------------------View Holders (Progress Holder and Retry Holder)------------------------------------*/

    private inner class DummyHeaderViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

        private inner class ProgressHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewWithTag<View>(123) as ProgressBar
        }
    }

    private inner class EmptyViewHolder internal constructor(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)

    private inner class ListEndHolder internal constructor(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)

    private inner class RetryHolder internal constructor(
        itemView: View,
        @IdRes retryTrigerViewId: Int
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var onRetryListener: OnRetryListener? = null
        override fun onClick(v: View) {
            if (onRetryListener != null) {
                clearFooter()
                onRetryListener!!.onRetry()
            }
        }

        fun setOnRetryListener(onRetryListener: OnRetryListener?) {
            this.onRetryListener = onRetryListener
        }

        init {
            if (retryTrigerViewId == 0) {
                itemView.setOnClickListener(this)
            } else {
                itemView.findViewById<View>(retryTrigerViewId).setOnClickListener(this)
            }
        }
    }


}
