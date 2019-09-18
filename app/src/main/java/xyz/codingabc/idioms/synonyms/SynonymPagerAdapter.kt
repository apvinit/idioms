package xyz.codingabc.idioms.synonyms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.codingabc.idioms.data.model.Synonym

class SynonymPagerAdapter(fm: FragmentManager, private val synonyms: List<Synonym>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SynonymDetailFragment(synonyms[position])
    }

    override fun getCount(): Int {
        return synonyms.size
    }
}