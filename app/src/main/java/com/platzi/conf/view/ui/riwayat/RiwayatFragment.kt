package com.platzi.conf.view.ui.riwayat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.platzi.conf.R
import com.platzi.conf.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class RiwayatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_riwayat, container, false)

        val tab_layout: TabLayout = v.findViewById(R.id.tab_layout)
        val view_pager: ViewPager = v.findViewById(R.id.view_pager)
        view_pager.offscreenPageLimit = 3
        setupViewPager(view_pager)

        tab_layout.setupWithViewPager(view_pager)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.bnvMenu?.visibility = View.VISIBLE

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViewPager(view_pager: ViewPager) {
        val viewPagerAdapter =
            ViewPagerAdapter(
                childFragmentManager
            )

        viewPagerAdapter.addFragment(RiwayatPinjamFragment(), "Peminjaman")
        viewPagerAdapter.addFragment(RiwayatBayarFragment(), "Pembayaran")
        viewPagerAdapter.addFragment(RiwayatCairFragment(), "Pencairan")

        view_pager.adapter = viewPagerAdapter
    }
}
