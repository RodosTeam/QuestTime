package dev.rodosteam.questtime.screen.common

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.ActivityMainBinding
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.utils.LocaleManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Init repo from resources
        QuestMetaRepoJson.initRes(resources, applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navBar = binding.navBar as NavigationBarView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_library,
                R.id.navigation_external,
                R.id.navigation_editor,
                R.id.navigation_settings -> navBar.visibility = View.VISIBLE
                else -> {
                    navBar.visibility = View.GONE
                }
            }
        }
        setupActionBarWithNavController(navController, AppBarConfiguration(
            setOf(
                R.id.navigation_library,
                R.id.navigation_external,
                R.id.navigation_editor,
                R.id.navigation_settings
            )))

        navBar.setupWithNavController(navController)
        // set library as homepage
        navBar.selectedItemId = R.id.navigation_library
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase!!))
    }

}
