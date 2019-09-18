package xyz.codingabc.idioms.synonyms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_synonym_detail.*

import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Synonym

class SynonymDetailFragment(private val synonym: Synonym) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_synonym_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sd_word.text = synonym.word
        sd_word_meaning.text = synonym.meaning
        sd_word_synonym.text = synonym.synonym
    }


}
