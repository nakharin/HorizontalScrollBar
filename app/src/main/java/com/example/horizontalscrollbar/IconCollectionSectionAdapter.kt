package com.example.horizontalscrollbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horizontalscrollbar.databinding.ListEntryIconCollectionSectionBinding

class IconCollectionSectionAdapter : RecyclerView.Adapter<IconCollectionSectionViewHolder>() {

    private var onItemClickListener: OnItemClickListener<IconCollectionSectionUiModel>? = null
    private  var list = listOf<IconCollectionSectionUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconCollectionSectionViewHolder {
        val binding = ListEntryIconCollectionSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IconCollectionSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IconCollectionSectionViewHolder, position: Int) {
        holder.bind(position, list[position], onItemClickListener)
    }

    fun setOnItemClickLister(onItemClickListener: OnItemClickListener<IconCollectionSectionUiModel>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount() = list.size

    fun addItems(list: List<IconCollectionSectionUiModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
