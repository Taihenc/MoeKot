package com.taihen.moekot

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.model.MoeItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.moe_detail)

//        val imageView = findViewById<ImageView>(R.id.moe_image)
        setContentView(R.layout.main_activity)

        val recyclerView: RecyclerView = findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        val moeItems = listOf<MoeItem>(
            MoeItem(R.drawable.preview_chainsaw, "Chainsaw Man"),
            MoeItem(R.drawable.preview_jujutsu, "Jujutsu Kaisen"),
            MoeItem(R.drawable.preview_oshino, "Oshi no Ko"),
            MoeItem(R.drawable.preview_yofukashi, "Yofukashi no Uta"),
        )

        println("moeItems: $moeItems")
        val adapter = MoeAdapter(moeItems)
        recyclerView.adapter = adapter
    }
}
