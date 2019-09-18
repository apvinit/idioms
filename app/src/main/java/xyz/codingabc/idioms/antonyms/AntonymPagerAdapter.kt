package xyz.codingabc.idioms.antonyms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.codingabc.idioms.data.model.Antonym

class AntonymPagerAdapter(fm: FragmentManager, private val antonyms: List<Antonym>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return AntonymDetailFragment(antonyms[position])
    }

    override fun getCount() = antonyms.size
}