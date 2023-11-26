package com.taihen.moekot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.model.MoeItem
import com.taihen.moekot.ui.theme.MoeKotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView: RecyclerView = findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        val moeItems = listOf<MoeItem>(
            MoeItem(R.drawable.chainsaw, "Chainsaw Man"),
            MoeItem(R.drawable.jujutsu, "Jujutsu Kaisen"),
            MoeItem(R.drawable.oshino, "Oshi no Ko"),
            MoeItem(R.drawable.yofukashi, "Yofukashi no Uta"),
        )

        println("moeItems: $moeItems")
        val adapter = MoeAdapter(moeItems)
        recyclerView.adapter = adapter
    }
}
