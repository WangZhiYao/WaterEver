package me.zhiyao.waterever.ui.widgets

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import me.zhiyao.waterever.databinding.LayoutMainMenuBinding
import me.zhiyao.waterever.exts.dp2px
import kotlin.math.sqrt

/**
 *
 * @author WangZhiYao
 * @date 2020/8/16
 */
class MainMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var isMenuOpening = false
    private val binding = LayoutMainMenuBinding.inflate(LayoutInflater.from(context), this, true)

    private val radius = 120.dp2px(context).toFloat()
    private val duration: Long = 300

    private val openMenuAnimatorSet = AnimatorSet()
    private val closeMenuAnimatorSet = AnimatorSet()

    private var onMenuItemClickListener: OnMenuItemClickListener? = null

    init {
        binding.fabMain.setOnClickListener { toggle() }
        binding.fabAddPlant.setOnClickListener {
            onMenuItemClickListener?.onAddPlantClicked()
            closeMenu(false)
        }
        binding.fabAddGrowthRecord.setOnClickListener {
            onMenuItemClickListener?.onAddGrowthRecordClicked()
            closeMenu(false)
        }
        binding.fabAddReminder.setOnClickListener {
            onMenuItemClickListener?.onAddReminderClicked()
            closeMenu(false)
        }

        val mainOpenAnimator =
            ObjectAnimator.ofFloat(
                binding.fabMain,
                "rotation",
                0f,
                45f
            )
        val plantOpenAnimator =
            ObjectAnimator.ofFloat(
                binding.fabAddPlant,
                "translationY",
                0f,
                -radius
            )
        val recordOpenXAnimator = ObjectAnimator.ofFloat(
            binding.fabAddGrowthRecord,
            "translationX",
            0f,
            -(radius / sqrt(2.toDouble()).toFloat())
        )
        val recordOpenYAnimator = ObjectAnimator.ofFloat(
            binding.fabAddGrowthRecord,
            "translationY",
            0f,
            -(radius / sqrt(2.toDouble()).toFloat())
        )
        val reminderOpenAnimator = ObjectAnimator.ofFloat(
            binding.fabAddReminder,
            "translationX",
            0f,
            -radius
        )

        openMenuAnimatorSet.playTogether(
            mainOpenAnimator,
            plantOpenAnimator,
            recordOpenXAnimator,
            recordOpenYAnimator,
            reminderOpenAnimator
        )
        openMenuAnimatorSet.duration = duration
        openMenuAnimatorSet.doOnStart { setMenuStatus(true) }

        val mainCloseAnimator =
            ObjectAnimator.ofFloat(
                binding.fabMain,
                "rotation",
                45f,
                0f
            )
        val plantCloseAnimator =
            ObjectAnimator.ofFloat(
                binding.fabAddPlant,
                "translationY",
                -radius,
                0f
            )
        val recordCloseXAnimator = ObjectAnimator.ofFloat(
            binding.fabAddGrowthRecord,
            "translationX",
            -(radius / sqrt(2.toDouble()).toFloat()),
            0f
        )
        val recordCloseYAnimator = ObjectAnimator.ofFloat(
            binding.fabAddGrowthRecord,
            "translationY",
            -(radius / sqrt(2.toDouble()).toFloat()),
            0f
        )
        val reminderCloseAnimator = ObjectAnimator.ofFloat(
            binding.fabAddReminder,
            "translationX",
            -radius,
            0f
        )

        closeMenuAnimatorSet.playTogether(
            mainCloseAnimator,
            plantCloseAnimator,
            recordCloseXAnimator,
            recordCloseYAnimator,
            reminderCloseAnimator
        )
        closeMenuAnimatorSet.duration = duration
        closeMenuAnimatorSet.doOnEnd { setMenuStatus(false) }
    }

    inline fun setOnMenuItemClickListener(
        crossinline onAddPlantClicked: () -> Unit = {},
        crossinline onAddGrowthRecordClicked: () -> Unit = {},
        crossinline onAddReminderClicked: () -> Unit = {}
    ) {
        setOnMenuItemClickListener(object : OnMenuItemClickListener {
            override fun onAddPlantClicked() = onAddPlantClicked()
            override fun onAddGrowthRecordClicked() = onAddGrowthRecordClicked()
            override fun onAddReminderClicked() = onAddReminderClicked()
        })
    }

    fun setOnMenuItemClickListener(onMenuItemClickListener: OnMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener
    }

    fun isMenuOpening(): Boolean {
        return isMenuOpening
    }

    private fun toggle() {
        if (!isMenuOpening) {
            openMenu()
        } else {
            closeMenu(true)
        }
    }

    fun openMenu() {
        openMenuAnimatorSet.start()
    }

    fun closeMenu(animated: Boolean) {
        closeMenuAnimatorSet.start()
        if (!animated) {
            closeMenuAnimatorSet.end()
        }
    }

    private fun setMenuStatus(opened: Boolean) {
        isMenuOpening = opened
        val visibility = if (opened) View.VISIBLE else View.GONE
        binding.fabAddPlant.visibility = visibility
        binding.fabAddGrowthRecord.visibility = visibility
        binding.fabAddReminder.visibility = visibility
    }

    interface OnMenuItemClickListener {

        fun onAddPlantClicked()

        fun onAddGrowthRecordClicked()

        fun onAddReminderClicked()
    }
}