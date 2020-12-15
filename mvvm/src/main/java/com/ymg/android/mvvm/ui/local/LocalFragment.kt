package com.ymg.android.mvvm.ui.local

import com.kennyc.view.MultiStateView
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.BR
import com.ymg.android.mvvm.base.BaseFragment
import com.ymg.android.mvvm.databinding.FragmentLocalBinding
import com.ymg.android.mvvm.ui.share.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class LocalFragment : BaseFragment<FragmentLocalBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_local
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SharedViewModel {
        return sharedViewModel
    }

    override fun bindView() {
        getViewDataBinding().view = this
        observe()
    }



    private fun observe() {
        sharedViewModel.goodItems.observe(this, {
            if (it.isNullOrEmpty()) {
                getViewDataBinding().stateView.viewState = MultiStateView.ViewState.EMPTY
            } else {
                getViewDataBinding().stateView.viewState = MultiStateView.ViewState.CONTENT
            }
        })
    }
}