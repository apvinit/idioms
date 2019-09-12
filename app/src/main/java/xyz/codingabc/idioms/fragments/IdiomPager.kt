package xyz.codingabc.idioms.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_idiom_pager.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.adapters.IdiomPagerAdapter
import xyz.codingabc.idioms.data.db.AppDatabase

class IdiomPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idiom_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val keyword = arguments?.getString("keyword") ?: ""

        if (keyword.isNotEmpty()) {
            AppDatabase.getInstance(context!!).idiomDao().getBySearch("%$keyword%").observe(this, Observer {
                idiom_pager.adapter = IdiomPagerAdapter(childFragmentManager, it)
                idiom_pager.currentItem = position
            })
        } else {
            AppDatabase.getInstance(context!!).idiomDao().getAll().observe(this, Observer {
                idiom_pager.adapter = IdiomPagerAdapter(childFragmentManager, it)
                idiom_pager.currentItem = position
            })
        }

    }
}
