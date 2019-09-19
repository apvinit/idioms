package xyz.codingabc.idioms.onewordsubstitutions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_one_word_subs_pager.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class OneWordSubsPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one_word_subs_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val keyword = arguments?.getString("keyword") ?: ""

        if (keyword.isNotEmpty()) {
            AppDatabase.getInstance(context!!).oneWordSubstitutionDao().getBySearch("%$keyword%")
                .observe(this, Observer {
                    ows_pager.adapter = OneWordSubsPagerAdapter(
                        childFragmentManager,
                        it
                    )
                    ows_pager.currentItem = position
                })
        } else {
            AppDatabase.getInstance(context!!).oneWordSubstitutionDao().getAll()
                .observe(this, Observer {
                    ows_pager.adapter = OneWordSubsPagerAdapter(
                        childFragmentManager,
                        it
                    )
                    ows_pager.currentItem = position
                })
        }
    }

}
