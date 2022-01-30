package com.example.vkapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.databinding.FragmentNewsBinding
import com.example.vkapp.model_package.model.ModelMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception


class NewsFragment : Fragment(), IsConnectable {

    private var _newsBinding: FragmentNewsBinding? = null
    private val newsBinding get() = _newsBinding!!

    var retrofitBuilder = RetrofitCreator().getUserAccessToken()
    lateinit var responseBody: ModelMain
    lateinit var adapter: NewsAdapter
    lateinit var recyclerView: RecyclerView


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
        recyclerView = view.findViewById(R.id.rv_news_id)

        news()


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun news() {
        GlobalScope.launch(Dispatchers.Main) {

            try {
                val response = retrofitBuilder.newsJSONResponse(
                    "50",
                    getToken,
                    VERSION
                ).awaitResponse()
                if (response.isSuccessful) {
                    responseBody = response.body()!!
                    adapter = NewsAdapter(responseBody, requireContext().applicationContext)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                    adapter.setOnItemClickListener { test, id ->
                        val action =
                            NewsFragmentDirections.actionNewsFragmentToPostFragment(test, id)
                        val connectionFlag = activity?.let { isOnline(it.applicationContext) }
                        if (connectionFlag == true) {
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(
                                activity?.applicationContext,
                                "No Internet Connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("error", "ERROR")
                news()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _newsBinding = null
    }

}