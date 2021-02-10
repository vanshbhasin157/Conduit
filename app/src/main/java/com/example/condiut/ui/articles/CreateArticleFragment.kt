package com.example.condiut.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.condiut.R
import com.example.condiut.databinding.ArticleCreateFragmentBinding

class CreateArticleFragment : Fragment() {

    private var _binding: ArticleCreateFragmentBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    val list = listOf<String>('a'.toString(), 'b'.toString(), 'c'.toString())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = ArticleCreateFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            submitButton.setOnClickListener {
                    articleViewModel.createArticle(
                        titleTextView.text.toString(),
                        descriptionTextView.text.toString(),
                        bodyTextView.text.toString(),
                        list
                    )
                findNavController().navigate(R.id.nav_my_feed)
            }
        }

    }

}