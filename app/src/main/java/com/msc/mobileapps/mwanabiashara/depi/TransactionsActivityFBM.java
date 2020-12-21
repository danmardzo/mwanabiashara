/*
 * Copyright (c) 2019.
 * Daniel M. Musila
 *
 * @author Daniel Mutiso
 * @email dan.musila@gmail.com
 * All rights reserved.
 *
 * Redistribution and use in any forms, with or without
 * modification, are not permitted without express written permission or contractual
 * agreement with the author.
 */
package com.msc.mobileapps.mwanabiashara.depi;


import com.msc.mobileapps.mwanabiashara.ui.NewTransactionFragment;
import com.msc.mobileapps.mwanabiashara.ui.TransactionListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TransactionsActivityFBM {
    @ContributesAndroidInjector
    abstract TransactionListFragment transactionListFragment();

    @ContributesAndroidInjector
    abstract NewTransactionFragment newTransactionFragment();
}
