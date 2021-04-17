package ml.zedlabs.weatherapptask.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.weatherapptask.R
import ml.zedlabs.weatherapptask.databinding.FragmentListBinding

@AndroidEntryPoint
class ListFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.listToSearch)
        }

        viewModel.getFavCityData()
        Log.e("12", "1->onCreateView: ${viewModel.data.value?.get(0)?.city}")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}