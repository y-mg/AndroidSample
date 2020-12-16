package com.ymg.android.room.ui.bookmark

import android.widget.Toast
import com.kennyc.view.MultiStateView
import com.ymg.android.room.BR
import com.ymg.android.room.R
import com.ymg.android.room.base.BaseFragment
import com.ymg.android.room.databinding.FragmentBookMarkBinding
import com.ymg.android.room.ui.share.SharedViewModel
import com.ymg.android.room.util.event.EventObserver
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class BookMarkFragment : BaseFragment<FragmentBookMarkBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_book_mark
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
        sharedViewModel.bookMarkNavigator.observe(this, EventObserver {
            when (it) {
                // 삭제 완료
                BookMarkNavigator.DELETE_SUCCESS -> {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_delete_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // 삭제 실패
                BookMarkNavigator.DELETE_FAIL -> {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_delete_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }
        })


        sharedViewModel.bookMarkItems?.observe(this, {
            if (it.isNullOrEmpty()) {
                getViewDataBinding().stateView.viewState = MultiStateView.ViewState.EMPTY
            } else {
                getViewDataBinding().stateView.viewState = MultiStateView.ViewState.CONTENT
            }
        })
    }
}