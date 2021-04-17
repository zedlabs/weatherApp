package ml.zedlabs.weatherapptask.ui

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse

class CityListAdapter(private val list: List<String>, private val onClick: (String) -> Unit) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    class CityViewHolder(tv: TextView) : RecyclerView.ViewHolder(tv) {
        var textView : TextView = tv

        init {
            tv.setOnClickListener {  }
        }

        fun bind(data: String, clickListener: (String) -> Unit) {
            itemView.setOnClickListener { clickListener(data) }
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
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size
}