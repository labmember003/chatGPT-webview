package com.falcon.chatgptbasedai

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.falcon.chatgptbasedai.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val url = "https://chat.openai.com/chat"
        binding.resultWebView.settings.javaScriptEnabled = true
        binding.resultWebView.settings.userAgentString = "Android"
        binding.resultWebView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.imagePendingAnimation.visibility = View.GONE
                binding.resultWebView.visibility = View.VISIBLE
            }
        }
        binding.resultWebView.loadUrl(url)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(ContentValues.TAG, "Fragment back pressed invoked")
                    if (binding.resultWebView.canGoBack()) {
                        binding.resultWebView.goBack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
            )
        return binding.root
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}