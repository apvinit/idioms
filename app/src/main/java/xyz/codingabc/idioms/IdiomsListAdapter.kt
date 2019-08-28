package xyz.codingabc.idioms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import xyz.codingabc.idioms.data.model.Idiom

class IdiomsListAdapter(private val idioms: List<Idiom>) : RecyclerView.Adapter<IdiomsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_idiom, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = idioms.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindIdiom(idioms[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idiomText: MaterialTextView = view.findViewById(R.id.idiom_text)
        private val idiomMeaning: MaterialTextView = view.findViewById(R.id.idiom_meaning)
        fun bindIdiom(idiom: Idiom) {
            idiomText.text = idiom.text
            idiomMeaning.text = idiom.meaning
        }
    }
}