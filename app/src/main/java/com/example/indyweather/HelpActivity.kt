package com.example.indyweather

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.indyweather.databinding.HelpActivityBinding

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: HelpActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HelpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Enables the back button in the action bar

        loadPreferences()

        binding.saveButton.setOnClickListener {
            savePreferences()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()  // This will close the current activity and return to the previous one
        return true
    }

    private fun loadPreferences() {
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val city = sharedPreferences.getString("city", "Indianapolis") // Default to Indianapolis
        binding.editCity.setText(city)
    }

    private fun savePreferences() {
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("city", binding.editCity.text.toString())
            apply()
        }
    }
}
