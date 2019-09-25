package xyz.codingabc.idioms.antonyms


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_antonym_pager.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class AntonymPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antonym_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val keyword = arguments?.getString("keyword") ?: ""

        if (keyword.isNotEmpty()) {
            AppDatabase.getInstance(context!!).antonymDao().getBySearch("%$keyword%")
                .observe(this, Observer {
                    antonym_pager.adapter = AntonymPagerAdapter(childFragmentManager, it)
                    antonym_pager.currentItem = position
                })
        } else {
            AppDatabase.getInstance(context!!).antonymDao().getAll().observe(this, Observer {
                antonym_pager.adapter = AntonymPagerAdapter(childFragmentManager, it)
                antonym_pager.currentItem = position
            })
        }
    }

}
