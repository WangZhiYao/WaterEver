package me.zhiyao.waterever.ui.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
abstract class BaseActivity : AppCompatActivity() {

    var showBack = false

    fun showBack() {
        supportActionBar?.run {
            showBack = true
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (showBack && item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}