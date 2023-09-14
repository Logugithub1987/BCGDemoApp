package com.example.newsapplication.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.newsapplication.R
import com.example.newsapplication.databinding.NewsItemBinding
import com.example.newsapplication.models.DataForDetails
import com.example.newsapplication.models.Result


class NewsListAdapter(
    private val context: Context,
    var newsList: List<Result>,
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var dataForDetails : DataForDetails

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var navController: NavController? = null
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(FitCenter(), RoundedCorners(16))
        with(holder){
            with(newsList[position]){
                binding.tvLangName.text = this.title
                Glide.with(context)
                    .load(this.multimedia[0].url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .apply(requestOptions)
                    .onlyRetrieveFromCache(false)
                    .into(binding.ivNewsImg)
            }

            itemView.setOnClickListener {
                with(newsList[position]){
                    dataForDetails = DataForDetails(this.title , this.subsection,this.abstract, this.multimedia[0].url)
                }
                navController = Navigation.findNavController(itemView)
                val action = NewsListFragmentDirections.actionFirstFragmentToSecondFragment(dataForDetails, position)
                navController!!.navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
