package me.zhiyao.waterever.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @author WangZhiYao
 * @date 2020/7/16
 */
abstract class BaseFragment : Fragment() {

    private var isViewInit = false
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = setRootView(inflater, container, savedInstanceState)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isViewInit) {
            super.onViewCreated(view, savedInstanceState)
            initView()
            initData()
            isViewInit = true
        }
    }

    abstract fun setRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract fun initView()

    abstract fun initData()
}