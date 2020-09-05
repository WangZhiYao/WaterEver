package me.zhiyao.waterever.ui.reminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.ActivityNewReminderBinding
import me.zhiyao.waterever.ui.base.BaseActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/8/17
 */
@AndroidEntryPoint
class NewReminderActivity : BaseActivity() {

    private lateinit var mBinding: ActivityNewReminderBinding

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewReminderActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewReminderBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun showBack(): Boolean = true
}