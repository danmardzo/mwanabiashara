package com.msc.mobileapps.mwanabiashara;

import android.app.Activity;
import android.app.Application;

import com.msc.mobileapps.mwanabiashara.depi.AppComponent;
import com.msc.mobileapps.mwanabiashara.depi.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MwanaBiasharaApp extends Application
        implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

