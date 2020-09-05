package me.zhiyao.waterever.ui.main.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentReminderBinding
import me.zhiyao.waterever.ui.base.BaseFragment

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class ReminderFragment : BaseFragment() {

    private lateinit var binding: FragmentReminderBinding

    private val viewModel by viewModels<ReminderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }
}