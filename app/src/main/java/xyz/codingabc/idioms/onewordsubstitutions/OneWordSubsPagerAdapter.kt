package xyz.codingabc.idioms.onewordsubstitutions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import xyz.codingabc.idioms.data.model.OneWordSubstitution

class OneWordSubsPagerAdapter(fm: FragmentManager, private val idioms: List<OneWordSubstitution>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return OneWordSubstitutionDetail(idioms[position])
    }

    override fun getCount() = idioms.size
}