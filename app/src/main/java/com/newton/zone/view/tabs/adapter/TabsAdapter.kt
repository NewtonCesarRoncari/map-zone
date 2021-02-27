package com.newton.zone.view.tabs.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.newton.zone.R
import com.newton.zone.view.fragment.ListClientFragment
import com.newton.zone.view.fragment.ListLeadFragment

class TabsAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ListLeadFragment()
        } else {
            ListClientFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.getString(R.string.leads)
            1 -> return context.getString(R.string.clientes)
        }
        return null
    }
}