package xyz.codingabc.idioms.antonyms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Antonym

@Suppress("UNCHECKED_CAST")
class AntonymListAdapter(private val antonyms: List<Antonym>) :
    RecyclerView.Adapter<AntonymListAdapter.ViewHolder>(), Filterable {

    private var antonymsFiltered: List<Antonym> = antonyms
    private var searchKeyword = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_antonym, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = antonymsFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindAntonym(antonymsFiltered[position])

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString()
                searchKeyword = keyword
                antonymsFiltered = if (keyword.isEmpty()) antonyms else {
                    val filteredList = mutableListOf<Antonym>()
                    for (antonym in antonyms) {
                        if (antonym.word.contains(keyword, true)) {
                            filteredList.add(antonym)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = antonymsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                antonymsFiltered = results?.values as List<Antonym>
                notifyDataSetChanged()
            }
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val antonymWord = view.findViewById<MaterialTextView>(R.id.antonym_word)

        init {
            view.findViewById<ConstraintLayout>(R.id.antonym_container).setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("position", layoutPosition)
                bundle.putString("keyword", searchKeyword)
                it.findNavController()
                    .navigate(
                        R.id.action_destination_antonym_to_antonymPager,
                        bundle
                    )
            }
        }

        fun bindAntonym(antonym: Antonym) {
            antonymWord.text = antonym.word
        }
    }
}