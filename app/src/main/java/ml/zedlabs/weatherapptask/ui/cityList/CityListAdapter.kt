package ml.zedlabs.weatherapptask.ui.cityList

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import android.annotation.SuppressLint

class CityListAdapter(
    private val list: MutableList<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>(), Filterable {

    private val originalList = ArrayList(list)

    class CityViewHolder(tv: TextView) : RecyclerView.ViewHolder(tv) {
        var textView: TextView = tv

        fun bind(data: String, clickListener: (String) -> Unit) {
            itemView.setOnClickListener { clickListener(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val tv = TextView(parent.context)
        tv.setPadding(8, 8, 16, 16)
        tv.textSize = 28F
        return CityViewHolder(tv)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.textView.text = list[position]
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size

    override fun getFilter(): Filter {
        return cityFilter
    }

    private val cityFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<String>()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(originalList)
            } else {
                originalList.forEach {
                    if (it.contains(constraint.toString(), true))
                        filteredList.add(it)
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            list.clear()
            list.addAll(results?.values as List<String>)
            notifyDataSetChanged()
        }

    }


}