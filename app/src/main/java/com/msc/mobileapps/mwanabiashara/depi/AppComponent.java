/*
  Copyright © 2018-present, Daniel Mutiso.
  All rights reserved.
  Redistribution and use in source and binary forms, with or without modification,
  are not permitted without express written permission.
 */
package com.msc.mobileapps.mwanabiashara.depi;

import android.app.Application;

import com.msc.mobileapps.mwanabiashara.MwanaBiasharaApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ViewModelModule.class,
        CommonModule.class,
        RoomModule.class
})
public interface AppComponent {
    void inject(MwanaBiasharaApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
