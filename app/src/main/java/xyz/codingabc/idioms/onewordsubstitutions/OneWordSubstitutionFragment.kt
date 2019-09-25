package xyz.codingabc.idioms.onewordsubstitutions


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.fragment_one_word_substitution.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase
import xyz.codingabc.idioms.data.model.OneWordSubstitution
import java.util.*

class OneWordSubstitutionFragment : Fragment() {

    private lateinit var owsAdapter: OneWordSubstitutionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_one_word_substitution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        owsList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).oneWordSubstitutionDao().getAll().observe(this, Observer {
            owsAdapter = OneWordSubstitutionListAdapter(it)
            owsList.adapter = owsAdapter
            setUpScrollView(it)
        })
    }

    private fun setUpScrollView(data: List<OneWordSubstitution>) {
        val locale = Locale.getDefault()
        ows_scroller.setupWithRecyclerView(
            owsList,
            { position ->
                val item = data[position] // Get your model object
                // or fetch the section at [position] from your database
                FastScrollItemIndicator.Text(
                    item.word.substring(
                        0,
                        1
                    ).toUpperCase(locale) // Grab the first letter and capitalize it
                ) // Return a text indicator
            }
        )
        ows_scroller_thumb.setupWithFastScroller(ows_scroller)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search one word substitutions"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                owsAdapter.filter.filter(newText)
                return true
            }
        })
    }

}
