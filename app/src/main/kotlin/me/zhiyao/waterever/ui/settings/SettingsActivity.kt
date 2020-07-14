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

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showBack()
    }
}