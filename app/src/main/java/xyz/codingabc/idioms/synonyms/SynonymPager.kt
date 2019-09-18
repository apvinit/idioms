package xyz.codingabc.idioms.synonyms


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_synonym_pager.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class SynonymPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_synonym_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val keyword = arguments?.getString("keyword") ?: ""

        if (keyword.isNotEmpty()) {
            AppDatabase.getInstance(context!!).synonymDao().getBySearch("%$keyword%")
                .observe(this, Observer {
                    synonym_pager.adapter = SynonymPagerAdapter(childFragmentManager, it)
                    synonym_pager.currentItem = position
                })
        } else {
            AppDatabase.getInstance(context!!).synonymDao().getAll().observe(this, Observer {
                synonym_pager.adapter = SynonymPagerAdapter(childFragmentManager, it)
                synonym_pager.currentItem = position
            })
        }
    }


}
