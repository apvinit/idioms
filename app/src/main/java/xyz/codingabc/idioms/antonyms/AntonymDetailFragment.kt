package xyz.codingabc.idioms.antonyms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_antonym_detail.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Antonym

class AntonymDetailFragment(private val antonym: Antonym) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antonym_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        word.text = antonym.word
        word_meaning.text = antonym.meaning
        word_antonym.text = antonym.antonym
    }


}
