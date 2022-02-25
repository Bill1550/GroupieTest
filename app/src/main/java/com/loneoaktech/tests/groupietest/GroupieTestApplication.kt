package com.loneoaktech.tests.groupietest

import android.app.Application
import timber.log.Timber

class GroupieTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}