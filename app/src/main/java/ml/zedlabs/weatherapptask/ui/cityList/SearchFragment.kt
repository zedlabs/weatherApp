package ml.zedlabs.weatherapptask.ui.cityList

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.weatherapptask.R
import ml.zedlabs.weatherapptask.databinding.FragmentSearchBinding
import ml.zedlabs.weatherapptask.util.Cdata
import androidx.appcompat.widget.SearchView
import ml.zedlabs.weatherapptask.ui.MainViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var clAdapter: CityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clAdapter = CityListAdapter(Cdata.cityList) {
            viewModel.getCityWeatherData(it)
            Toast.makeText(requireContext(), "$it added!", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewCityList.apply {
            adapter = clAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                clAdapter.filter.filter(newText)
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}