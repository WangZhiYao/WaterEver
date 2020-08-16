package me.zhiyao.waterever.ui.plant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import me.zhiyao.waterever.databinding.ActivityNewPlantBinding
import me.zhiyao.waterever.ui.base.BaseActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/8/15
 */
class NewPlantActivity : BaseActivity() {

    private lateinit var mBinding: ActivityNewPlantBinding

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewPlantBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun showBack(): Boolean = true
}