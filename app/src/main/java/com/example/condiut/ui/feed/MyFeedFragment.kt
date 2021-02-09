package com.example.condiut.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condiut.R
import com.example.condiut.databinding.FragmentFeedBinding

class MyFeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter { openArticle(it) }
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        _binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.feedRecyclerView?.adapter = feedAdapter



        return _binding?.root
    }

    fun openArticle(articleId: String) {
        findNavController().navigate(R.id.action_nav_Globalfeed_to_nav_article,
            bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyFeed()
        viewModel.feed.observe({ lifecycle }) {
            feedAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}