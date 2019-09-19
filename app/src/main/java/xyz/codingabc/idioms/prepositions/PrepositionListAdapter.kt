package xyz.codingabc.idioms.prepositions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Preposition

class PrepositionListAdapter(private val prepositions: List<Preposition>): RecyclerView.Adapter<PrepositionListAdapter.ViewHolder>(), Filterable {

    private var prepositionsFiltered: List<Preposition> = prepositions
    private var searchKeyword = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_preposition, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = prepositionsFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPreposition(prepositionsFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString()
                searchKeyword = keyword
                prepositionsFiltered = if (keyword.isEmpty()) prepositions else {
                    val filteredList = mutableListOf<Preposition>()
                    for (preposition in prepositions) {
                        if (preposition.word.contains(keyword, true)) {
                            filteredList.add(preposition)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = prepositionsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                prepositionsFiltered = results?.values as List<Preposition>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val wordPreposition = view.findViewById<MaterialTextView>(R.id.word_preposition)

        fun bindPreposition(preposition: Preposition) {
            wordPreposition.text = "${preposition.word} ${preposition.preposition}"
        }
    }
}