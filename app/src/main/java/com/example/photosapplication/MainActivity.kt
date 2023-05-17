package com.example.photosapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.photosapplication.dagger.AppComponent
import com.example.photosapplication.dagger.DaggerAppComponent
import com.example.photosapplication.dagger.RetrofitModule
import com.example.photosapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var appComponent: AppComponent

    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAppComponent()

        appComponent.injectActivity(this)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = findNavController(R.id.navHost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newPhotosFragment, R.id.popularPhotosFragment
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }


    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .retrofitModule(RetrofitModule(this))
            .build()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}