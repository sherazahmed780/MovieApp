package com.example.movieapp.utils

import android.app.Activity
import com.kaopiz.kprogresshud.KProgressHUD
import javax.inject.Singleton

class MyProgressBar {
    companion object {
        private lateinit var progress: KProgressHUD
        @Singleton
        fun showProgress(activity: Activity) {
            progress = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show()
        }
        fun hideProgress() {
            progress.dismiss()
        }


    }
}