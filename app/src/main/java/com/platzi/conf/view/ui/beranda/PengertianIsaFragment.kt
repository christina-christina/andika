package com.platzi.conf.view.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.platzi.conf.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pengertian_isa.*


/**
 * A simple [Fragment] subclass.
 */
class PengertianIsaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_pengertian_isa, container, false)

        val url = "https://www.meratas.com/blog/guide-income-share-agreement"
        val view = rootView.findViewById<View>(R.id.wv_pengertianIsa) as WebView
        view.settings.javaScriptEnabled = true
        view.loadUrl(url)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.bnvMenu?.visibility = View.GONE
        super.onViewCreated(view, savedInstanceState)

    }
}
