package com.ymg.android.paging.base


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

            // LiveData 가 View 의 LifeCycle 관찰하여 View 와 생명주기를 같이할 수 있게 하기 위함
            lifecycleOwner = this@BaseFragment

            // 뷰를 강제로 업데이트
            // 데이터 바인딩 시 즉시 반영을 위해 사용
            executePendingBindings()
        }

        bindView()
    }



    fun getViewDataBinding() : V {
        return viewDataBinding
    }
}