package me.zhiyao.waterever.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.ActivityMainBinding
import me.zhiyao.waterever.ui.base.BaseActivity
import me.zhiyao.waterever.ui.plant.NewPlantActivity
import me.zhiyao.waterever.ui.record.NewGrowthRecordActivity
import me.zhiyao.waterever.ui.reminder.NewReminderActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        initView()
    }

    private fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_plant_list,
                R.id.nav_reminder,
                R.id.nav_settings
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.appBarMain.mainMenu.setOnMenuItemClickListener(
            onAddPlantClicked = { NewPlantActivity.start(this@MainActivity) },
            onAddGrowthRecordClicked = { NewGrowthRecordActivity.start(this@MainActivity) },
            onAddReminderClicked = { NewReminderActivity.start(this@MainActivity) }
        )
    }

    override fun showBack(): Boolean = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            binding.appBarMain.mainMenu.isMenuOpening() -> {
                binding.appBarMain.mainMenu.closeMenu()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}