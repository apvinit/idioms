package xyz.codingabc.idioms.idiomsandphrases

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.codingabc.idioms.data.model.Idiom

class IdiomPagerAdapter(fm: FragmentManager, private val idioms: List<Idiom>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return IdiomDetailFragment(idioms[position])
    }

    override fun getCount() = idioms.size
}