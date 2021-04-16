package ml.zedlabs.weatherapptask.ui

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityListAdapter(private val list: List<String>) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    class CityViewHolder(tv: TextView) : RecyclerView.ViewHolder(tv) {
        var textView : TextView = tv

        init {
            tv.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val tv = TextView(parent.context)
        tv.setPadding(8, 8, 8, 16)
        tv.textSize = 24F
        return CityViewHolder(tv)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}