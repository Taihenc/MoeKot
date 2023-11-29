package com.taihen.moekot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.ui.moeGrid.MoeAdapter
import com.taihen.moekot.ui.moeGrid.MoeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var moeViewModel: MoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView: RecyclerView = findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager   = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        val moeAdapter = MoeAdapter(emptyList())
        recyclerView.adapter = moeAdapter

        // Observe changes in moeItemList
        moeViewModel.moeItemList.observe(this) { moeList ->
            moeAdapter.updateData(moeList)
        }

        // Fetch MoeItems when the activity is created
        moeViewModel.fetchMoeItems()
    }
}

