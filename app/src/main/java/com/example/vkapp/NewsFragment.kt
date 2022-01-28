package com.example.vkapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.databinding.FragmentNewsBinding
import com.example.vkapp.model.ModelMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class NewsFragment : Fragment() {

//    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(mutableListOf<ModelMain>()) }

    private var _newsBinding: FragmentNewsBinding? = null
    private val newsBinding get() = _newsBinding!!

    var retrofitBuilder = RetrofitCreator().getUserAccessToken()
    lateinit var responseBody: ModelMain
    lateinit var adapter: NewsAdapter
    lateinit var recyclerView: RecyclerView


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
//        initRecycler()
        recyclerView = view.findViewById(R.id.rv_news_id)

        news()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun news() {
        GlobalScope.launch(Dispatchers.Main) {

            val response = retrofitBuilder.newsJSONResponse(
                getToken,
                VERSION
            ).awaitResponse()

            if (response.isSuccessful) {
                responseBody = response.body()!!
                adapter = NewsAdapter(responseBody)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        }
    }


//    private fun initRecycler() = with(newsBinding) {
//        rvNewsId.adapter = newsAdapter
//        rvNewsId.layoutManager = LinearLayoutManager(requireContext())
//
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newsBinding = null
    }

}