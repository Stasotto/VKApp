package com.example.vkapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.vkapp.const.URL_AUTH_GET_TOKEN


class AuthorizationFragment : Fragment() {

    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAuth()
    }

    private fun loadAuth() {
        webView = requireView().findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled
        webView.loadUrl(URL_AUTH_GET_TOKEN)

//        Log.d("CW", urlWebView)
    }

}