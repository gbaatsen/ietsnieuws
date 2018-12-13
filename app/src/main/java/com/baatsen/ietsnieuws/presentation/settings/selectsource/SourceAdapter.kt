package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.SourceItemBinding
import com.baatsen.ietsnieuws.domain.model.Source


class SourceAdapter(private val clickListener: ClickListener) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    private lateinit var sourceList: List<Source>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SourceItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.source_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sourceList[position])

    }

    override fun getItemCount(): Int {
        return if (::sourceList.isInitialized) sourceList.size else 0
    }

    fun updateSourceList(sourceList: List<Source>) {
        this.sourceList = sourceList
    }

    inner class ViewHolder(private val binding: SourceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.source = source
            binding.root.setOnClickListener { clickListener.onClick(source.id) }
            binding.executePendingBindings()
        }
    }

    interface ClickListener {
        fun onClick(id: String?)
    }
}