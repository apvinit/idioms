package xyz.codingabc.idioms.prepositions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_preposition_detail.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Preposition

class PrepositionDetailFragment(private val preposition: Preposition) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preposition_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        word_preposition.text = resources.getString(
            R.string.word_preposition,
            preposition.word,
            preposition.preposition
        )
        word_preposition_usage.text = resources.getString(
            R.string.word_preposition_usage,
            preposition.word,
            preposition.preposition,
            preposition.nouns
        )
    }


}
