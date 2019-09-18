package xyz.codingabc.idioms.idiomsandphrases


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_idiom_detail.*
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Idiom

class IdiomDetailFragment(private val idiom: Idiom) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idiom_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        word.text = idiom.text
        word_meaning.text = idiom.meaning
    }
}
