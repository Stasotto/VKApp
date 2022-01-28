package com.example.vkapp

import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.vkapp.databinding.FragmentNewsBinding
import com.example.vkapp.model.ModelMain


class NewsFragment : Fragment() {

    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(mutableListOf<ModelMain>()) }

    private var _newsBinding: FragmentNewsBinding? = null
    private val newsBinding get() = _newsBinding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _newsBinding = FragmentNewsBinding.inflate(inflater, container, false)

        return newsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

    }

    private fun initRecycler() = with(newsBinding) {
        rvNewsId.adapter = newsAdapter
        rvNewsId.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newsBinding = null
    }

}