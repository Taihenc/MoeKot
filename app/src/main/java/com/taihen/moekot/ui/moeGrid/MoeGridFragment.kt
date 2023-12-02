package com.taihen.moekot.ui.moeGrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.data.graphql.MoeClient
import com.taihen.moekot.model.MoeItem
import com.taihen.moekot.model.MoeListViewModel
import com.taihen.moekot.model.MoeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MoeGridFragment: Fragment(R.layout.fragment_moe_grid) {
    private val viewModel: MoeListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var moeClient: MoeClient
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
        val adapter = MoeAdapter(emptyList(), selectedMoeItem)

        viewModel.moeItemList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        recyclerView.adapter = adapter

//        this should be temporary
        viewModel.viewModelScope.launch {
            try {
                val moeList: List<MoeItem> = moeClient.fetchMangaItems(1, 10);
                viewModel.updateMoeList(moeList)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
