package com.example.horizontalscrollbar

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.horizontalscrollbar.databinding.ListEntryIconCollectionSectionBinding

class IconCollectionSectionViewHolder(private val binding: ListEntryIconCollectionSectionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        position: Int,
        model: IconCollectionSectionUiModel?,
        onItemClickListener: OnItemClickListener<IconCollectionSectionUiModel>?
    ) {
        if (model == null) return

        binding.apply {
            tvIconCollectionSection.text = model.name
            ivIconCollectionSection.load(model.imageUrl)
        }

        itemView.setOnClickListener {
            onItemClickListener?.invoke(it, model, position)
        }
    }
}
