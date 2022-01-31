package com.example.vkapp


import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.example.vkapp.const.URL_AUTH_GET_TOKEN
import com.example.vkapp.const.getToken
import com.example.vkapp.const.s
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AuthorizationFragment : Fragment() {

    private lateinit var webView: WebView
    private var urlLink: String = ""
    private val dm: TokenData by activityViewModels()


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
        netCheck()


        dm.token.observe(activity as LifecycleOwner, {
            getToken = it
            Log.d("CW", getToken)
            Log.d("!!!CW", s.length.toString())
            Log.d("!!!CW", getToken.length.toString())
            when (s.length) {
                it.length -> destroyView()
            }
        })
    }

    private fun destroyView() {

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
                val tokenBufferS = accessS.substringBefore("%2526expires_in")
                val token = tokenBufferS.substringAfter("%253D")
                dm.token.value = token

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun netCheck() {
        var flag = activity?.let { isOnline(it.applicationContext) }
        if (flag == false) {
            Toast.makeText(context, "Connect Network", Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                while (flag == false) {
                    val flag2 = activity?.let { isOnline(it.applicationContext) }
                    flag = flag2 == true
                    loadAuth()
                }
            }
        }
    }
}
