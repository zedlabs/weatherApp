package ml.zedlabs.weatherapptask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ml.zedlabs.weatherapptask.databinding.FragmentDetailsBinding
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(args.wData)
    }

    private fun setupView(data: CityWeatherData?) {
        binding.apply {
            tvCityNameDet.text = data?.city
            tvDescDet.text = data?.weatherDescription
            tvTmpDet.text = data?.currentTemp.toString()
            tvMintmpDet.text = data?.tempMin.toString()
            tvMaxtmpDet.text = data?.tempMax.toString()
            tvHumidityDet.text = data?.humidity.toString()
            tvPressureDet.text = data?.pressure.toString()
            tvUpdDet.text = data?.time
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}