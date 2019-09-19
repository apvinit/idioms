package xyz.codingabc.idioms.prepositions


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.fragment_preposition_list.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase
import xyz.codingabc.idioms.data.model.Preposition
import java.util.*

class PrepositionListFragment : Fragment() {

    private lateinit var prepositionListAdapter: PrepositionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_preposition_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepositionList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).prepositionDao().getAll().observe(this, Observer {
            prepositionListAdapter = PrepositionListAdapter(it)
            prepositionList.adapter = prepositionListAdapter
            setUpScrollView(it)
        })
    }

    private fun setUpScrollView(antonyms: List<Preposition>) {
        val locale = Locale.getDefault()
        p_scroller.setupWithRecyclerView(
            prepositionList,
            { position ->
                val item = antonyms[position] // Get your model object
                // or fetch the section at [position] from your database
                FastScrollItemIndicator.Text(
                    item.word.substring(
                        0,
                        1
                    ).toUpperCase(locale) // Grab the first letter and capitalize it
                ) // Return a text indicator
            }
        )
        p_scroller_thumb.setupWithFastScroller(p_scroller)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search Prepositions"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                prepositionListAdapter.filter.filter(newText)
                return true
            }
        })
    }

}
