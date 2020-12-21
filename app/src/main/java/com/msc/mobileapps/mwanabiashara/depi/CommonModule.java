/*
  Copyright © 2018-present, Daniel Mutiso.
  All rights reserved.
  Redistribution and use in source and binary forms, with or without modification,
  are not permitted without express written permission.
 */
package com.msc.mobileapps.mwanabiashara.depi;


import com.msc.mobileapps.mwanabiashara.LoginActivity;
import com.msc.mobileapps.mwanabiashara.MainActivity;
import com.msc.mobileapps.mwanabiashara.ui.TransactionsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CommonModule {
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector(modules = TransactionsActivityFBM.class)
    abstract TransactionsActivity transactionsActivity();

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}






