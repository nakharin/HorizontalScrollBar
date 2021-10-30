package com.example.horizontalscrollbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horizontalscrollbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var iconCollectionSectionAdapter: IconCollectionSectionAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iconCollectionSectionAdapter = IconCollectionSectionAdapter()
        binding.apply {
            recyclerView.adapter = iconCollectionSectionAdapter
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2, RecyclerView.HORIZONTAL, false)
            horizontalScrollBar.attachToRecyclerView(recyclerView)
        }
    }

    override fun onStart() {
        super.onStart()
        val listIconOf = listOf(
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
//            IconCollectionSectionUiModel(id = "1", name = "0 THB Delivery Fee", imageUrl = "https://picsum.photos/200/300"),
        )
        iconCollectionSectionAdapter.addItems(listIconOf)
    }
}