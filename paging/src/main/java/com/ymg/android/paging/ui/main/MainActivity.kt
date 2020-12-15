package com.ymg.android.paging.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.ymg.android.paging.R
import com.ymg.android.paging.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 소프트 키보드 공간을 만드릭 위해 메인 윈도우의 크기를 재조정하지 않음
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        // 액티비티 전환 애니메이션 해제
        overridePendingTransition(0, 0)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (navHostFragment != null) {
            val currentFragment = navHostFragment.childFragmentManager.backStackEntryCount

            when {
                currentFragment >= 1 -> {
                    findNavController(R.id.fragmentContainerView).popBackStack()
                }

                else -> {
                    finish()
                    super.onBackPressed()
                }
            }
        }
    }

    override fun finish() {
        super.finish()

        // 액티비티 전환 애니메이션 해제
        overridePendingTransition(0, 0)
    }
}