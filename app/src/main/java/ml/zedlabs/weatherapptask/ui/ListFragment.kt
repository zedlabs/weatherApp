package ml.zedlabs.weatherapptask.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.weatherapptask.R
import ml.zedlabs.weatherapptask.databinding.FragmentListBinding
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.util.Cdata

@AndroidEntryPoint
class ListFragment : Fragment() , FavListAdapter.OnItemClickListener{

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val favAdapter = FavListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.listToSearch)
        }

        //viewModel.getFavCityData()
        binding.recyclerViewFav.apply {
            adapter = favAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.data.observe(viewLifecycleOwner, {
            Log.e("listFragment", "1->onCreateView: ${it.size}")
            favAdapter.submitList(it)
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: CityWeatherData) {

    }
}