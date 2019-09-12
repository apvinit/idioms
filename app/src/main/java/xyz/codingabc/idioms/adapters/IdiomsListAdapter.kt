package xyz.codingabc.idioms.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import xyz.codingabc.idioms.R
import xyz.codingabc.idioms.data.model.Idiom
import java.util.*

@Suppress("UNCHECKED_CAST")
class IdiomsListAdapter(private val idioms: List<Idiom>) :
    RecyclerView.Adapter<IdiomsListAdapter.ViewHolder>(), Filterable {

    private var _idiomsFiltered: List<Idiom> = idioms

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_idiom, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = _idiomsFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindIdiom(_idiomsFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString().toLowerCase(Locale.getDefault())
                _idiomsFiltered = if (keyword.isEmpty()) idioms else {
                    val filteredList = mutableListOf<Idiom>()
                    for (idiom in idioms) {
                        if (idiom.text.contains(keyword) || idiom.meaning.contains(keyword)) {
                            filteredList.add(idiom)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = _idiomsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                _idiomsFiltered = results?.values as List<Idiom>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val idiomText: MaterialTextView = view.findViewById(R.id.idiom_text)
        private val idiomMeaning: MaterialTextView = view.findViewById(R.id.idiom_meaning)
        fun bindIdiom(idiom: Idiom) {
            idiomText.text = idiom.text
            idiomMeaning.text = idiom.meaning
            val bundle = Bundle()
            bundle.putInt("position", layoutPosition)
            view.setOnClickListener {
                it.findNavController()
                    .navigate(
                        R.id.action_destination_idioms_and_phrases_to_idiomPager,
                        bundle
                    )
            }
        }
    }
}