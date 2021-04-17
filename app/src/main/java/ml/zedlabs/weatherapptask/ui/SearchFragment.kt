package ml.zedlabs.weatherapptask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.weatherapptask.databinding.FragmentSearchBinding
import ml.zedlabs.weatherapptask.util.Cdata

@AndroidEntryPoint
class SearchFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerViewCityList.apply {
            adapter = CityListAdapter(Cdata.cityList){
                viewModel.getCityWeatherData(it)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}