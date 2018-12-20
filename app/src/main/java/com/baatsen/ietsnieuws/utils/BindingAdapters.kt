package com.baatsen.ietsnieuws.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("flag")
fun setFlag(view: ImageView, flag: Int) {
    view.setImageResource(flag)
}


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("orientation")
fun setOrientation(recyclerView: RecyclerView, orientation: Int) {
    if (recyclerView.layoutManager is LinearLayoutManager) {
        (recyclerView.layoutManager as LinearLayoutManager).orientation = orientation
    }
}