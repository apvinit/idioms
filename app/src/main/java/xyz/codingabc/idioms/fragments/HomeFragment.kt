package xyz.codingabc.idioms.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

import xyz.codingabc.idioms.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        antonym_card.setOnClickListener {
            findNavController().navigate(R.id.action_destination_home_to_destination_antonym)
        }

        synonym_card.setOnClickListener {
            findNavController().navigate(R.id.action_destination_home_to_destination_synonym)
        }

        one_word_substitution_card.setOnClickListener {
            findNavController().navigate(R.id.action_destination_home_to_destination_one_word_substitution)
        }

        idioms_and_phrases_card.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_idiomsListFragment)
        }
    }
}
