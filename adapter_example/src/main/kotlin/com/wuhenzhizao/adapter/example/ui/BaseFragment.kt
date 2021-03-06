package com.wuhenzhizao.adapter.example.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


/**
 * Created by liufei on 2017/10/12.
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected lateinit var binding: DB

    protected abstract fun getContentViewId(): Int

    protected abstract fun initViews()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getContentViewId(), container, false)
        val rootView = binding.root
        val parent = rootView.parent
        if (parent != null && parent is ViewGroup) {
            parent.removeView(rootView)
        }
        initViews()
        return binding.root
    }

    protected fun simulateLoadData(block: () -> Unit) {
        binding.root.postDelayed({
            block()
        }, 1500)
    }

    protected fun showToast(toast: CharSequence, duration: Int) {
        Toast.makeText(context, toast, duration).show()
    }

    protected fun showToast(toast: CharSequence) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }
}