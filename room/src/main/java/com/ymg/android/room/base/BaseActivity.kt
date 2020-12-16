package com.ymg.android.room.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding



abstract class BaseActivity<V: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    private lateinit var viewDataBinding: V

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    abstract fun getBindingVariable(): Int
    abstract fun getViewModel(): VM

    abstract fun bindView()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewDataBinding()
        bindView()
    }

    private fun setViewDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        viewDataBinding.apply {
            lifecycleOwner = this@BaseActivity
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
        }
    }

    fun getViewDataBinding() : V {
        return viewDataBinding
    }
}