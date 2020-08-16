package me.zhiyao.waterever.ui.record

import android.content.Context
import android.content.Intent
import android.os.Bundle
import me.zhiyao.waterever.databinding.ActivityNewGrowthRecordBinding
import me.zhiyao.waterever.ui.base.BaseActivity
import me.zhiyao.waterever.ui.plant.NewPlantActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/8/17
 */
class NewGrowthRecordActivity : BaseActivity() {

    private lateinit var mBinding: ActivityNewGrowthRecordBinding

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewGrowthRecordBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun showBack(): Boolean = true
}