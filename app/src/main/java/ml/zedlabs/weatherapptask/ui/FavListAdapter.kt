package ml.zedlabs.weatherapptask.ui

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
        }

        fun bind(item: CityWeatherData) {
            binding.apply {
                tvCityName.text = item.city
//                rankTextView.text = adapterPosition.formattedPosition()
//                linkTextView.text = story.url?.stripUrl()
//                infoTextView.text = Pair(story.score, story.by).pointsAndAuthorString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: CityWeatherData)
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