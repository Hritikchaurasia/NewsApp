package com.hritik.newsapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hritik.newsapp.R
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.databinding.NewsitemBinding

class NewsAdapter  : RecyclerView.Adapter<NewsAdapter.MyArticleViewHolder>() {

    inner class MyArticleViewHolder(private val binding: NewsitemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .into(ivArticleImage)
                tvTitle.text = article.title
                tvPublishedAt.text = article.publishedAt
                tvDescription.text = article.description
            }
       }
        }
    val differ = AsyncListDiffer(this, differCallBack)

    companion object {
        private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArticleViewHolder {
        val binding =
            NewsitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return   MyArticleViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        if(article!=null){
            holder.bind(article)
        }

    }

}