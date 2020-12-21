/*
  Copyright © 2018-present, Daniel Mutiso.
  All rights reserved.
  Redistribution and use in source and binary forms, with or without modification,
  are not permitted without express written permission.
 */
package com.msc.mobileapps.mwanabiashara.depi;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.msc.mobileapps.mwanabiashara.db.ViewModelKey;
import com.msc.mobileapps.mwanabiashara.db.viewmodel.DatabaseViewModel;
import com.msc.mobileapps.mwanabiashara.db.viewmodel.MyViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DatabaseViewModel.class)
    abstract ViewModel viewModel(DatabaseViewModel databaseViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);
}


