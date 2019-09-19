package xyz.codingabc.idioms.prepositions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.codingabc.idioms.data.model.Preposition

class PrepositionPagerAdapter(fm: FragmentManager, private val prepositions: List<Preposition>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PrepositionDetailFragment(prepositions[position])
    }

    override fun getCount() = prepositions.size
}