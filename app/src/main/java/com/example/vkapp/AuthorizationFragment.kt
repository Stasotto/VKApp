package com.example.vkapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.example.vkapp.const.URL_AUTH_GET_TOKEN
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import com.example.vkapp.const.s
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AuthorizationFragment : Fragment() {

    private lateinit var webView: WebView
    private var urlLink: String = ""
    private val dm: TokenData by activityViewModels()
    var retrofitBuilder = RetrofitCreator().getUserAccessToken()


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

        webView = requireView().findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled

        loadAuth()

        dm.token.observe(activity as LifecycleOwner, {
            getToken = it
            Log.d("CW", getToken)
            Log.d("!!!CW", s.length.toString())
            Log.d("!!!CW", getToken.length.toString())
            when(s.length) {
                it.length -> destroyView()
            }
        })
    }

    //а вот здесь если вызывать, то все работает шикарно
    //я хз с чем это связано
    //Вызов метода по кнопке в функции снизу, если что

//    private fun retrofitResponse() {
//        GlobalScope.launch {
//
//            // Стас, чекни как работает @Query, надеюсь в рабочем коде тебе будет лучше понятно
//            // Я там тебе комменты оставил
//            val response = retrofitBuilder.newsJSONResponse(
//                getToken,
//                VERSION
//            ).awaitResponse()
//
//            if (response.isSuccessful) {
//                val data = response.body()!! // Здесь, кстати, не забываем "!!" после вызова тела,
//                // чтобы погружаясь в data class не втыкать "?" по 100500 раз в 1 строке
//                val k = (data.response.items[0].post_type).toString()
//                Log.d("CWW", k)
//            }
//        }
//    }

    private fun destroyView() {

//            retrofitResponse()
            webView.destroy()
           view?.findNavController()?.navigate(R.id.action_authorizationFragment_to_newsFragment)

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

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

    }
}