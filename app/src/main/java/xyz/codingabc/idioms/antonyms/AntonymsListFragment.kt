package xyz.codingabc.idioms.antonyms


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.fragment_antonyms_list.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase
import xyz.codingabc.idioms.data.model.Antonym
import java.util.*

class AntonymsListFragment : Fragment() {

    private lateinit var antonymListAdapter: AntonymListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_antonyms_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        antonymList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).antonymDao().getAll().observe(this, Observer {
            antonymListAdapter = AntonymListAdapter(it)
            antonymList.adapter = antonymListAdapter
            setUpScrollView(it)
        })
    }

    private fun setUpScrollView(antonyms: List<Antonym>) {
        val locale = Locale.getDefault()
        al_scroller.setupWithRecyclerView(
            antonymList,
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
        al_scroller_thumb.setupWithFastScroller(al_scroller)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search Antonyms"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                antonymListAdapter.filter.filter(newText)
                return true
            }
        })
    }

}
