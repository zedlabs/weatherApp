package ml.zedlabs.weatherapptask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.weatherapptask.R
import ml.zedlabs.weatherapptask.databinding.FragmentListBinding
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

@AndroidEntryPoint
class ListFragment : Fragment(), FavListAdapter.OnItemClickListener {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val favAdapter = FavListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.listToSearch)
        }

        binding.recyclerViewFav.apply {
            adapter = favAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.data.observe(viewLifecycleOwner, {
            favAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: CityWeatherData) {
        val action = ListFragmentDirections.listToDetails(item)
        findNavController().navigate(action)
    }

    override fun onDeleteClick(item: CityWeatherData) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok) { _, _ ->
                    viewModel.deleteCityData(item)
                }
                setNegativeButton(R.string.cancel) { _, _ -> }
                setTitle("Delete?")
            }.create()
        }
        alertDialog?.show()
    }
}