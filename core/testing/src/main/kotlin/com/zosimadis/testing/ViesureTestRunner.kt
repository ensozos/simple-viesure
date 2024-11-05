package com.zosimadis.testing

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class ViesureTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?,
    ): Application = super.newApplication(cl, className, context)
}
