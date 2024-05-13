package com.example.indyweather

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.indyweather.databinding.ActivityMainBinding
import com.example.indyweather.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()
    private val CHANNEL_ID = "weather_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()  // Setup the notification channel

        viewModel.weatherData.observe(this) { weather ->
            binding.temperatureText.text = "${weather.temperature}°C"
            binding.description.text = weather.weather.description // Correctly access the nested description
            sendNotification(weather.temperature, weather.weather.description)  // Send a notification whenever the weather data is updated
        }

        setupNavigation()  // Setup navigation for the bottom navigation view
    }

    private fun setupNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_details -> {
                    startActivity(Intent(this, DetailActivity::class.java))
                    true
                }
                R.id.navigation_help -> {
                    startActivity(Intent(this, HelpActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(temperature: Double, description: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_weather_icon)  // Ensure you have a drawable resource named `ic_weather_icon`
            .setContentTitle("Current Weather")
            .setContentText("Temperature: $temperature°C. $description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(1, builder.build())  // Send the notification with ID 1
        }
    }
}
