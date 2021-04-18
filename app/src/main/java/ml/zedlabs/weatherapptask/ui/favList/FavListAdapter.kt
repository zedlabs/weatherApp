package ml.zedlabs.weatherapptask.ui.favList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ml.zedlabs.weatherapptask.databinding.FavListItemBinding
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

class FavListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<CityWeatherData, FavListAdapter.FavListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavListViewHolder {
        val binding =
            FavListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class FavListViewHolder(private val binding: FavListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
            }
            binding.deleteBtn.setOnClickListener {
                listener.onDeleteClick(getItem(adapterPosition))
            }
        }

        fun bind(item: CityWeatherData) {
            binding.apply {
                tvCityName.text = item.city
                tvWeatherDesc.text = item.weatherDescription
                tvTemp.text = item.currentTemp.toString()
                tvDt.text = item.time
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: CityWeatherData)
        fun onDeleteClick(item: CityWeatherData)
    }

    class DiffCallback : DiffUtil.ItemCallback<CityWeatherData>() {

        override fun areItemsTheSame(oldItem: CityWeatherData, newItem: CityWeatherData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CityWeatherData,
            newItem: CityWeatherData
        ): Boolean =
            oldItem == newItem
    }
}