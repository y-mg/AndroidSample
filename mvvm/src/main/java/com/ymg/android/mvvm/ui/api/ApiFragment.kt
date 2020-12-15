package com.ymg.android.mvvm.ui.api

import androidx.appcompat.widget.AppCompatTextView
import com.kennyc.view.MultiStateView
import com.ymg.android.mvvm.base.BaseFragment
import com.ymg.android.mvvm.databinding.FragmentApiBinding
import com.ymg.android.mvvm.ui.share.SharedViewModel
import com.ymg.android.mvvm.util.event.EventObserver
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.BR
import com.ymg.android.mvvm.databinding.StateErrorViewBinding



class ApiFragment : BaseFragment<FragmentApiBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_api
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
        sharedViewModel.apiNavigator.observe(this, EventObserver {
            when (it) {
                // 데이터 표시
                ApiNavigator.CHANGE_STATE_CONTENT -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.CONTENT
                }

                // 로딩 표시
                ApiNavigator.CHANGE_STATE_LOADING -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.LOADING
                }

                // 빈 데이터 표시
                ApiNavigator.CHANGE_STATE_EMPTY -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.EMPTY
                }

                else -> Unit
            }
        })

        sharedViewModel.errorNavigator.observe(this, EventObserver {
            if (!it.isNullOrEmpty()) {
                val errorLabel: AppCompatTextView = StateErrorViewBinding.inflate(layoutInflater).errorLabel
                errorLabel.text = it
            }

            // 에러 표시
            getViewDataBinding().stateView.viewState = MultiStateView.ViewState.ERROR
        })
    }
}