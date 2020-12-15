package com.ymg.android.paging.ui.sub.details

import com.ymg.android.paging.BR
import com.ymg.android.paging.R
import com.ymg.android.paging.databinding.FragmentDetailsBinding
import com.ymg.android.paging.base.BaseFragment
import com.ymg.android.paging.ui.vm.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class DetailsFragment : BaseFragment<FragmentDetailsBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_details
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SharedViewModel {
        return sharedViewModel
    }

    override fun bindView() {
        getViewDataBinding().view = this
    }
}