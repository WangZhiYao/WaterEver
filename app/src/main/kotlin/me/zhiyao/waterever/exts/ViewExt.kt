package me.zhiyao.waterever.exts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import me.zhiyao.waterever.databinding.ItemEmptyBinding
import me.zhiyao.waterever.databinding.ItemTipsBinding
import me.zhiyao.waterever.ui.widgets.EmptyViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
fun <T : View> T.showSnackBar(msgId: Int, length: Int) {
    showSnackBar(context.getString(msgId), length)
}

fun <T : View> T.showSnackBar(msg: String, length: Int) {
    showSnackBar(msg, length, null, {})
}

fun <T : View> T.showSnackBar(
    msgId: Int,
    length: Int,
    actionMessageId: Int,
    action: (View) -> Unit
) {
    showSnackBar(context.getString(msgId), length, context.getString(actionMessageId), action)
}

fun <T : View> T.showSnackBar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackBar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackBar.setAction(actionMessage) {
            action(this)
        }
    }
    snackBar.show()
}

fun ViewGroup.emptyViewHolder(): EmptyViewHolder {
    return EmptyViewHolder(
        ItemEmptyBinding.inflate(
            LayoutInflater.from(this.context),
            this,
            false
        )
    )
}

fun ViewGroup.tipsViewHolder(): TipsViewHolder {
    return TipsViewHolder(
        ItemTipsBinding.inflate(
            LayoutInflater.from(this.context),
            this,
            false
        )
    )
}
