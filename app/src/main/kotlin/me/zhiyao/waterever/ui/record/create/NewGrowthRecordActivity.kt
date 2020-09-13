package me.zhiyao.waterever.ui.record.create

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
import me.zhiyao.waterever.databinding.ActivityNewGrowthRecordBinding
import me.zhiyao.waterever.ui.base.BaseActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/8/17
 */
@AndroidEntryPoint
class NewGrowthRecordActivity : BaseActivity() {

    private lateinit var binding: ActivityNewGrowthRecordBinding

    private val viewModel by viewModels<NewGrowthRecordViewModel>()

    private lateinit var navController: NavController

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewGrowthRecordActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGrowthRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    override fun showBack(): Boolean = true

    private fun initView() {
        navController = findNavController(R.id.new_growth_record_nav_host)
        val appBarConfiguration = AppBarConfiguration.Builder()
            .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initData() {
        viewModel.isCompleted().observe(this, { completed ->
            if (completed) {
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