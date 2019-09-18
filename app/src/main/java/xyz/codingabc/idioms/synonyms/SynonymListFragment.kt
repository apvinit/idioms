package xyz.codingabc.idioms.synonyms


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.fragment_synonym_list.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase
import xyz.codingabc.idioms.data.model.Synonym
import java.util.*

class SynonymListFragment : Fragment() {

    private lateinit var synonymListAdapter: SynonymListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_synonym_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        synonymList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).synonymDao().getAll().observe(this, Observer {
            synonymListAdapter = SynonymListAdapter(it)
            synonymList.adapter = synonymListAdapter
            setUpScrollView(it)
        })
    }

    private fun setUpScrollView(antonyms: List<Synonym>) {
        val locale = Locale.getDefault()
        sl_scroller.setupWithRecyclerView(
            synonymList,
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
        sl_scroller_thumb.setupWithFastScroller(sl_scroller)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search Synonyms"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                synonymListAdapter.filter.filter(newText)
                return true
            }
        })
    }

}
