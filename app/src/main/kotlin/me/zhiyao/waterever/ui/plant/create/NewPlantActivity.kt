package me.zhiyao.waterever.ui.plant.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.ActivityNewPlantBinding
import me.zhiyao.waterever.ui.base.BaseActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/8/15
 */
@AndroidEntryPoint
class NewPlantActivity : BaseActivity() {

    private lateinit var binding: ActivityNewPlantBinding
    private val viewModel by viewModels<NewPlantViewModel>()

    private lateinit var navController: NavController

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    override fun showBack(): Boolean = false

    private fun initView() {
        navController = findNavController(R.id.new_plant_nav_host)
        val appBarConfiguration = AppBarConfiguration.Builder()
            .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initData() {
        viewModel.isCompleted().observe(this, { isComplete ->
            if (isComplete) {
                finish()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!navController.navigateUp()) {
            onBackPressed()
        }
        return true
    }
}