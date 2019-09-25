package xyz.codingabc.idioms.prepositions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_preposition_pager.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class PrepositionPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preposition_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val keyword = arguments?.getString("keyword") ?: ""

        if (keyword.isNotEmpty()) {
            AppDatabase.getInstance(context!!).prepositionDao().getBySearch("%$keyword%")
                .observe(this, Observer {
                    preposition_pager.adapter = PrepositionPagerAdapter(childFragmentManager, it)
                    preposition_pager.currentItem = position
                })
        } else {
            AppDatabase.getInstance(context!!).prepositionDao().getAll().observe(this, Observer {
                preposition_pager.adapter = PrepositionPagerAdapter(childFragmentManager, it)
                preposition_pager.currentItem = position
            })
        }

    }


}
