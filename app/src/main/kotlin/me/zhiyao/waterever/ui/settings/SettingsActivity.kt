package me.zhiyao.waterever.ui.settings

import android.os.Bundle
import me.zhiyao.waterever.databinding.ActivitySettingsBinding
import me.zhiyao.waterever.ui.base.BaseActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
class SettingsActivity : BaseActivity() {

    private lateinit var mBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun showBack(): Boolean = true
}