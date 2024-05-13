package com.example.indyweather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.indyweather.data.Resource
import com.example.indyweather.databinding.ActivityDetailsBinding
import com.example.indyweather.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.airQuality.observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    val airQuality = resource.data
                    if (airQuality != null) {
                        binding.aqiText.text = "AQI: ${airQuality.aqiData?.firstOrNull()?.aqi ?: "N/A"}"
                    }
                    if (airQuality != null) {
                        binding.pm25Text.text = "PM2.5: ${airQuality.aqiData?.firstOrNull()?.pm25 ?: "N/A"} µg/m³"
                    }
                    if (airQuality != null) {
                        binding.pm10Text.text = "PM10: ${airQuality.aqiData?.firstOrNull()?.pm10 ?: "N/A"} µg/m³"
                    }
                }
                is Resource.Error -> {
                    binding.aqiText.text = "Error: ${resource.message}"
                    binding.pm25Text.text = "Error"
                    binding.pm10Text.text = "Error"
                }
                is Resource.Loading -> {
                    binding.aqiText.text = "Loading..."
                    binding.pm25Text.text = "Loading..."
                    binding.pm10Text.text = "Loading..."
                }
            }
        }

        viewModel.fetchAirQuality() // Call without parameters
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


