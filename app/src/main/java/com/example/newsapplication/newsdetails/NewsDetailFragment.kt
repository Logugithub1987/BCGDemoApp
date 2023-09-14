package com.example.newsapplication.newsdetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapplication.NewsApp
import com.example.newsapplication.R
import com.example.newsapplication.databinding.NewsDetailFragmentBinding
import javax.inject.Inject

class NewsDetailFragment : Fragment() {

    private var _binding: NewsDetailFragmentBinding? = null
    private val args: NewsDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = (activity as AppCompatActivity).getString(R.string.second_fragment_label)

        binding.tvTitle.text = args.detailsData.title
        binding.tvSubsection.text = "Subsection : ${args.detailsData.subsection}"
        binding.tvAbstract.text=args.detailsData.abstract
        Glide.with(this.requireContext())
            .load(args.detailsData.img_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.ivNewsDetail)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as NewsApp).appComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}