package com.taihen.moekot.ui.moeGrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.data.graphql.MoeClient
import com.taihen.moekot.model.MoeItem
import com.taihen.moekot.model.MoeListViewModel
import com.taihen.moekot.model.MoeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MoeGridFragment: Fragment(R.layout.fragment_moe_grid) {
    private val viewModel: MoeListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var selectedMoeItem: MoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_moe_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.moe_grid_recycler_view)
        val adapter = MoeAdapter(selectedMoeItem)
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moeItemList.collectLatest { moeItems ->
                    println("moeItems: ${moeItems.size}")
                    adapter.submitList(moeItems)
                }
            }
        }

        recyclerView.addOnScrollListener(
            MoeGridScrollListener(
                viewModel,
                recyclerView.layoutManager as GridLayoutManager?
            )
        )

//        this should be temporary
        viewModel.fetchMoreMoeItems(1, 12)
    }

    class MoeGridScrollListener(viewModel: MoeListViewModel, layoutManager: RecyclerView.LayoutManager?) : RecyclerView.OnScrollListener() {
        private val _viewModel = viewModel
        private var _currentPage = 1

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (!_viewModel.loading.value && (visibleItemCount + firstVisibleItem) >= totalItemCount) {
                // Load more items
                _currentPage++
                _viewModel.fetchMoreMoeItems(_currentPage, 12)
            }
        }
    }
}
