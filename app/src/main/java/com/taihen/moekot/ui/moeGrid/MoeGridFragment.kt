package com.taihen.moekot.ui.moeGrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.model.AnilistClient
import com.taihen.moekot.model.MoeItem
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MoeGridFragment: Fragment(R.layout.fragment_moe_grid) {
    private val viewModel: MoeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var anilistClient: AnilistClient
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
        val adapter = MoeAdapter(emptyList())

        viewModel.moeItemList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        recyclerView.adapter = adapter

        viewModel.viewModelScope.launch {
            try {
                val moeList: List<MoeItem> = anilistClient.fetchMoeItems(0, 1, 60)
                viewModel.updateMoeList(moeList)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
