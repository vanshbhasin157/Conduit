package com.example.condiut.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.condiut.R
import com.example.condiut.databinding.ArticleFragmentBinding

class ArticleFragment : Fragment() {

    private var _binding: ArticleFragmentBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    private var articleId:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = ArticleFragmentBinding.inflate(inflater, container, false)
        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))

        }
        articleId?.let {
            articleViewModel.fetchArticle(it)
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}){

            _binding?.apply {
                titleTextView.text = it.title
                authorTextView.text = it.author.username
                dateTextView.text = it.updatedAt
                bodyTextView.text = it.body

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}