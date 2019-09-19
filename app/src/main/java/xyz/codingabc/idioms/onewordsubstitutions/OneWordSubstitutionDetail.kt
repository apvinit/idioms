package xyz.codingabc.idioms.onewordsubstitutions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one_word_substitution_detail.*
import kotlinx.android.synthetic.main.item_ows.ows_word
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.OneWordSubstitution

class OneWordSubstitutionDetail(private val ows: OneWordSubstitution) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one_word_substitution_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ows_word.text = ows.word
        ows_word_meaning.text = ows.meaning
    }
}
