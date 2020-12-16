package com.ymg.android.room.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment



abstract class BaseFragment<V: ViewDataBinding, VM: BaseViewModel> : Fragment() {

    private lateinit var viewDataBinding: V

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    abstract fun getBindingVariable(): Int
    abstract fun getViewModel(): VM

    abstract fun bindView()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(getBindingVariable(), getViewModel())

            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }

        bindView()
    }

    fun getViewDataBinding() : V {
        return viewDataBinding
    }
}