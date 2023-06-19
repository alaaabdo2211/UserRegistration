package com.code.myapplication.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.myapplication.domain.entities.MostViewData
import com.code.myapplication.databinding.FragmentDashboardBinding
import com.code.myapplication.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private var mostViewedAdapter: MostViewedAdapter? = null
    private lateinit var mostViewedList: ArrayList<MostViewData>
    private lateinit var viewModel: DashboardViewModel
    var isSelected = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        mostViewedList = ArrayList()
        lifecycleScope.launch {
            viewModel.state.collect { state ->

                binding.progressBar.isVisible = state.isLoading
                binding.rvUsers.isVisible = !state.isLoading

                if (state.mostViewedDataList.isNotEmpty()) {
                    Log.d("data body", state.mostViewedDataList.toString())
                    mostViewedList = state.mostViewedDataList as ArrayList<MostViewData>
                    setUserRecyclerView()
                }
            }

        }

        binding.svLayout.setOnRefreshListener {
            viewModel.getData()
            binding.svLayout.isRefreshing = false
        }

        binding.linearSort.setOnClickListener {
            sortData()
        }

        binding.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable) {
                mostViewedAdapter = MostViewedAdapter(mostViewedList.filter {
                    it.title.contains(s.toString(), true)
                } as ArrayList<MostViewData>, requireContext())
                binding.rvUsers.adapter = mostViewedAdapter
            }
        })

        binding.ivClear.setOnClickListener {
            binding.txtSearch.text = null
        }

    }

    private fun setUserRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        mostViewedAdapter = MostViewedAdapter(mostViewedList, requireContext())
        binding.rvUsers.adapter = mostViewedAdapter
    }

    private fun sortData() {
        if (!isSelected) {
            mostViewedList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.title }))
            mostViewedAdapter?.notifyDataSetChanged()
            setUserRecyclerView()
            isSelected = true
        } else {
            mostViewedList.sortWith(
                compareBy<MostViewData, String>(
                    String.CASE_INSENSITIVE_ORDER,
                    { it.title }).reversed()
            )
            mostViewedAdapter?.notifyDataSetChanged()
            setUserRecyclerView()
            isSelected = false

        }
    }
}