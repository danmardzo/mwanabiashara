/*
  Copyright © 2018-present, Daniel Mutiso.
  All rights reserved.
  Redistribution and use in source and binary forms, with or without modification,
  are not permitted without express written permission.
 */
package com.msc.mobileapps.mwanabiashara.depi;

import android.app.Application;

import androidx.room.Room;

import com.msc.mobileapps.mwanabiashara.db.ExpenseDao;
import com.msc.mobileapps.mwanabiashara.db.MyDb;
import com.msc.mobileapps.mwanabiashara.db.SaleDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    public RoomModule() {
    }

    @Provides
    @Singleton
    MyDb myDb(Application application) {
        return Room.databaseBuilder(application, MyDb.class, "biashara-db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    SaleDao saleDao(MyDb myDb) {
        return myDb.saleDao();
    }

    @Provides
    @Singleton
    ExpenseDao expenseDao(MyDb myDb) {
        return myDb.expenseDao();
    }
}
