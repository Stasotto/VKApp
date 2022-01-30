package com.example.vkapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.vkapp.databinding.FragmentNetWorkErrBinding

class NetWorkErrFragment : Fragment(), IsConnectable {

    private var _netErrBinding: FragmentNetWorkErrBinding? = null
    private val binding get() = _netErrBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _netErrBinding = FragmentNetWorkErrBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConnectId.setOnClickListener {
            val connectionFlag = activity?.let { isOnline(it.applicationContext) }
            if (connectionFlag == true) {
                Toast.makeText(activity?.applicationContext, "Connected", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.authorizationFragment)
            } else {
                Toast.makeText(
                    activity?.applicationContext,
                    "No Internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
            onDestroy()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _netErrBinding = null
    }
}



