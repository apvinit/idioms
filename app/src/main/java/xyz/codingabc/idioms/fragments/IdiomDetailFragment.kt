package xyz.codingabc.idioms.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_idiom_detail.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.db.AppDatabase

class IdiomDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idiom_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id")!!
        AppDatabase.getInstance(context!!).idiomDao().getById(id).observe(this, Observer {
            word.text = it.text
            word_meaning.text = it.meaning
        })
    }
}
