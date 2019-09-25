package xyz.codingabc.idioms.onewordsubstitutions

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
import xyz.codingabc.idioms.data.model.OneWordSubstitution
import java.util.*

@Suppress("UNCHECKED_CAST")
class OneWordSubstitutionListAdapter(private val oneWordSubs: List<OneWordSubstitution>) :
    RecyclerView.Adapter<OneWordSubstitutionListAdapter.ViewHolder>(), Filterable {

    private var oneWordSubsFiltered: List<OneWordSubstitution> = oneWordSubs

    private var searchKeyword = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ows, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = oneWordSubsFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindOneWordSub(oneWordSubsFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString().toLowerCase(Locale.getDefault())
                searchKeyword = keyword
                oneWordSubsFiltered = if (keyword.isEmpty()) oneWordSubs else {
                    val filteredList = mutableListOf<OneWordSubstitution>()
                    for (ows in oneWordSubs) {
                        if (ows.word.contains(keyword)) {
                            filteredList.add(ows)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = oneWordSubsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                oneWordSubsFiltered = results?.values as List<OneWordSubstitution>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val owsWord = view.findViewById<MaterialTextView>(R.id.ows_word)

        init {
            view.findViewById<ConstraintLayout>(R.id.ows_container).setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("position", layoutPosition)
                bundle.putString("keyword", searchKeyword)
                it.findNavController()
                    .navigate(
                        R.id.action_destination_one_word_substitution_to_oneWordSubsPager,
                        bundle
                    )
            }
        }

        fun bindOneWordSub(ows: OneWordSubstitution) {
            owsWord.text = ows.word
        }
    }
}