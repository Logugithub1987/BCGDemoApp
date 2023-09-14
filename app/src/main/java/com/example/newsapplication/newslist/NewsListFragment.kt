package com.example.newsapplication.newslist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.NewsApp
import com.example.newsapplication.databinding.NewsListFragmentBinding
import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.Result
import javax.inject.Inject

class NewsListFragment : Fragment() {

    private var _binding: NewsListFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var newsListAdapter: NewsListAdapter



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newsListViewModel by viewModels<NewsListViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = NewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as NewsApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvList.layoutManager = layoutManager
        newsListViewModel.getTopNews()

        newsListViewModel.newsListResponse.observe(viewLifecycleOwner){ response ->
            binding.progressBar.visibility = View.GONE
            when(response) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    loadListDataIntoRecyclerView(response.data.results)
                    newsListViewModel.tempResults = response.data.results
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        activity,
                        "Error : ${response.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.tvErrorMsg.text = response.message
                }
                is NetworkResult.Exception -> {
                    binding.tvErrorMsg.text = response.e.message
                    Toast.makeText(
                        activity,
                        "Exception: ${response.e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun loadListDataIntoRecyclerView(results: List<Result>) {
        newsListAdapter = NewsListAdapter(this.requireContext(), results)
        binding.rvList.adapter = newsListAdapter
        binding.rvList.visibility = View.VISIBLE


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}