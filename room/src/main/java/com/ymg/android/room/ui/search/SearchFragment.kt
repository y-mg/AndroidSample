package com.ymg.android.room.ui.search

import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.kennyc.view.MultiStateView
import com.ymg.android.room.base.BaseFragment
import com.ymg.android.room.ui.share.SharedViewModel
import com.ymg.android.room.util.event.EventObserver
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ymg.android.room.R
import com.ymg.android.room.BR
import com.ymg.android.room.databinding.FragmentSearchBinding
import com.ymg.android.room.databinding.StateErrorViewBinding



class SearchFragment : BaseFragment<FragmentSearchBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_search
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
        sharedViewModel.searchNavigator.observe(this, EventObserver {
            when (it) {
                // 데이터 표시
                SearchNavigator.CHANGE_STATE_CONTENT -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.CONTENT
                }

                // 로딩 표시
                SearchNavigator.CHANGE_STATE_LOADING -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.LOADING
                }

                // 빈 데이터 표시
                SearchNavigator.CHANGE_STATE_EMPTY -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.EMPTY
                }

                // 에러 표시
                SearchNavigator.CHANGE_STATE_ERROR -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.ERROR
                }

                // 저장 완료
                SearchNavigator.SAVE_SUCCESS -> {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_save_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // 저장 실패
                SearchNavigator.SAVE_FAIL -> {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_save_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }
        })
    }
}