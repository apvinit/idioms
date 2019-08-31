package xyz.codingabc.idioms.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.fragment_idioms_list.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.adapters.IdiomsListAdapter
import xyz.codingabc.idioms.data.db.AppDatabase
import xyz.codingabc.idioms.data.model.Idiom
import java.util.*

class IdiomsListFragment : Fragment() {

    private lateinit var idiomsListAdapter: IdiomsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_idioms_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idiomsList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).idiomDao().getAll().observe(this, Observer {
            idiomsListAdapter = IdiomsListAdapter(it)
            idiomsList.adapter = idiomsListAdapter
            setUpScrollView(it)
        })
    }

    private fun setUpScrollView(data: List<Idiom>) {
        val locale = Locale.getDefault()
        scroller.setupWithRecyclerView(
            idiomsList,
            { position ->
                val item = data[position] // Get your model object
                // or fetch the section at [position] from your database
                FastScrollItemIndicator.Text(
                    item.text.substring(
                        0,
                        1
                    ).toUpperCase(locale) // Grab the first letter and capitalize it
                ) // Return a text indicator
            }
        )
        scroller_thumb.setupWithFastScroller(scroller)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search idioms"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                idiomsListAdapter.filter.filter(newText)
                return true
            }
        })
    }
}
