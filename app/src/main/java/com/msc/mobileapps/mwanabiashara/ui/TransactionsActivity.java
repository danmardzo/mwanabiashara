package com.msc.mobileapps.mwanabiashara.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.msc.mobileapps.mwanabiashara.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class TransactionsActivity
        extends AppCompatActivity
        implements HasSupportFragmentInjector,
        TransactionListFragment.TransactionListFIL,
        NewTransactionFragment.TransactionListFIL{
    // SALES, D_EXPENSES, IN_EXPENSES
    public static String mode = "SALES";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActionBar mActionBar;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle("New order");
        }
    }

    @Override
    public void changeTitle(String title) {
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle(title);
        }
    }
}