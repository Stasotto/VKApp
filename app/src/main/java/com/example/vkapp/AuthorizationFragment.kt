package com.example.vkapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.vkapp.const.TOKEN
import com.example.vkapp.const.URL_AUTH_GET_TOKEN



class AuthorizationFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var buttonDestroy: Button

    private var urlLink: String = ""

    private val dm: TokenData by activityViewModels()


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

        buttonDestroy = requireView().findViewById(R.id.button_web_view_destroy)

        webView = requireView().findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled

        loadAuth()
        destroyView()

        dm.token.observe(activity as LifecycleOwner, {
            val tokeen = it
            Log.d("CW", tokeen)
        })
    }

    private fun destroyView() {

        buttonDestroy.setOnClickListener{
            webView.destroy()
        }
    }


    private fun loadAuth() {

        webView.loadUrl(URL_AUTH_GET_TOKEN)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                urlLink = webView.url.toString()
                Log.d("!!!CW", urlLink)
                val accessS = urlLink
                val s = accessS.substringBefore("%2526expires_in")
                val token = s.substringAfter("%253D")
                dm.token.value = token

                Log.d("!!!",token)

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

    }
}