package xyz.codingabc.idioms.synonyms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Synonym

@Suppress("UNCHECKED_CAST")
class SynonymListAdapter(private val synonyms : List<Synonym>) :
    RecyclerView.Adapter<SynonymListAdapter.ViewHolder>(), Filterable{

    private var synonymFiltered: List<Synonym> = synonyms
    private var searchKeyword = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_synonym, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = synonymFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSynonym(synonymFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString()
                searchKeyword = keyword
                synonymFiltered = if (keyword.isEmpty()) synonyms else {
                    val filteredList = mutableListOf<Synonym>()
                    for (synonym in synonyms) {
                        if (synonym.word.contains(keyword, true)) {
                            filteredList.add(synonym)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = synonymFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                synonymFiltered = results?.values as List<Synonym>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val synonymWord = view.findViewById<MaterialTextView>(R.id.synonym_word)

        fun bindSynonym(synonym: Synonym) {
            synonymWord.text = synonym.word
        }
    }
}