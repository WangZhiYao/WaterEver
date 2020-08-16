package me.zhiyao.waterever.ui.widgets

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import me.zhiyao.waterever.databinding.LayoutMainMenuBinding
import me.zhiyao.waterever.utils.dp2px
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

    private val mOpenMenuAnimatorSet = AnimatorSet()
    private val mCloseMenuAnimatorSet = AnimatorSet()

    private var mOnMenuItemClickListener: OnMenuItemClickListener? = null

    init {
        binding.fabMain.setOnClickListener { toggle() }
        binding.fabAddPlant.setOnClickListener {
            run {
                mOnMenuItemClickListener?.onAddPlantClicked()
                closeMenu()
            }
        }
        binding.fabAddGrowthRecord.setOnClickListener {
            run {
                mOnMenuItemClickListener?.onAddGrowthRecordClicked()
                closeMenu()
            }
        }
        binding.fabAddReminder.setOnClickListener {
            run {
                mOnMenuItemClickListener?.onAddReminderClicked()
                closeMenu()
            }
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

        mOpenMenuAnimatorSet.playTogether(
            mainOpenAnimator,
            plantOpenAnimator,
            recordOpenXAnimator,
            recordOpenYAnimator,
            reminderOpenAnimator
        )
        mOpenMenuAnimatorSet.duration = duration
        mOpenMenuAnimatorSet.addListener(onStart = {
            run {
                binding.fabAddPlant.visibility = View.VISIBLE
                binding.fabAddGrowthRecord.visibility = View.VISIBLE
                binding.fabAddReminder.visibility = View.VISIBLE
            }
        })

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

        mCloseMenuAnimatorSet.playTogether(
            mainCloseAnimator,
            plantCloseAnimator,
            recordCloseXAnimator,
            recordCloseYAnimator,
            reminderCloseAnimator
        )
        mCloseMenuAnimatorSet.duration = duration
        mCloseMenuAnimatorSet.addListener(onEnd = {
            run {
                binding.fabAddPlant.visibility = View.GONE
                binding.fabAddGrowthRecord.visibility = View.GONE
                binding.fabAddReminder.visibility = View.GONE
            }
        })
    }

    inline fun setOnMenuItemClickListener(
        crossinline onAddPlantClicked: () -> Unit = {},
        crossinline onAddGrowthRecordClicked: () -> Unit = {},
        crossinline onAddReminderClicked: () -> Unit = {}
    ) {
        val listener = object : OnMenuItemClickListener {
            override fun onAddPlantClicked() = onAddPlantClicked()
            override fun onAddGrowthRecordClicked() = onAddGrowthRecordClicked()
            override fun onAddReminderClicked() = onAddReminderClicked()
        }

        setOnMenuItemClickListener(listener)
    }

    fun setOnMenuItemClickListener(onMenuItemClickListener: OnMenuItemClickListener) {
        mOnMenuItemClickListener = onMenuItemClickListener
    }

    fun isMenuOpening(): Boolean {
        return isMenuOpening
    }

    private fun toggle() {
        if (!isMenuOpening) {
            openMenu()
        } else {
            closeMenu()
        }
    }

    private fun openMenu() {
        isMenuOpening = true
        mOpenMenuAnimatorSet.start()
    }

    fun closeMenu() {
        isMenuOpening = false
        mCloseMenuAnimatorSet.start()
    }

    interface OnMenuItemClickListener {
        fun onAddPlantClicked()
        fun onAddGrowthRecordClicked()
        fun onAddReminderClicked()
    }
}