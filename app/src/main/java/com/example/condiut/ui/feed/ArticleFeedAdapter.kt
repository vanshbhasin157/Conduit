package com.example.condiut.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.condiut.R
import com.example.condiut.databinding.ListItemArticlesBinding
import com.example.condiut.extensions.loadImage
import com.example.condiut.extensions.timeStamp
import models.entities.Article

class ArticleFeedAdapter(val onArticleClicked: (slug:String) -> Unit ): ListAdapter<Article, ArticleFeedAdapter.articleViewHolder>(
object :DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}
) {




    inner class articleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articleViewHolder {

        return articleViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_articles,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: articleViewHolder, position: Int) {
        ListItemArticlesBinding.bind(holder.itemView).apply {
            val article = getItem(position)
            authorTextView.text = article.author.username
            titleTextView.text = article.title
            articleBodyTextView.text = article.body
            dateTextView.timeStamp = article.createdAt
            imageView2.loadImage(article.author.image,true)

            root.setOnClickListener { onArticleClicked(article.slug) }
        }
    }
}