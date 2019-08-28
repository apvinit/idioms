package xyz.codingabc.idioms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_idioms_list.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class IdiomsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_idioms_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idiomsList.layoutManager = LinearLayoutManager(activity)
        AppDatabase.getInstance(context!!).idiomDao().getAll().observe(this, Observer {
            idiomsList.adapter = IdiomsListAdapter(it)
        })
    }


}
