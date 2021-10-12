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
            linemanIconRecyclerView.adapter = iconCollectionSectionAdapter
            linemanIconRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2, RecyclerView.HORIZONTAL, false)
            linemanHorizontalScrollBar.attachToRecyclerView(linemanIconRecyclerView)
        }
    }

    override fun onStart() {
        super.onStart()
        val listIconOf = listOf(
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
//            IconCollectionSectionUiModel(id = "0-thb-delivery-fee-bnmno", name = "0 THB Delivery Fee", imageUrl = "https://lineman.line-scdn.net/banners/1621311803138_0delivery.png1621311805975764838986__e4c0354a-9fbb-4582-8732-26c2597664b0.png"),
        )
        iconCollectionSectionAdapter.addItems(listIconOf)
    }
}