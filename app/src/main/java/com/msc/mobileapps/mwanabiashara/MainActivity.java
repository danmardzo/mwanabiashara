package com.msc.mobileapps.mwanabiashara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.card.MaterialCardView;
import com.msc.mobileapps.mwanabiashara.db.viewmodel.DatabaseViewModel;
import com.msc.mobileapps.mwanabiashara.ui.TransactionsActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainActivity extends AppCompatActivity implements HasActivityInjector, View.OnClickListener {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MaterialCardView salesCard;
    private MaterialCardView dexpCard;
    private MaterialCardView indexpCard;

    private TextView sumSales;
    private TextView sumDirects;
    private TextView sumIndirects;

    private TextView totalSales;
    private TextView totalDirects;
    private TextView totalIndirects;
    private TextView grossProfits;
    private TextView netProfits;

    private float tSales = 0;
    private float tDirects = 0;
    private float tIndirects = 0;

    private DatabaseViewModel databaseViewModel;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseViewModel = ViewModelProviders.of(this, viewModelFactory).get(DatabaseViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        salesCard = findViewById(R.id.salesCard);
        dexpCard = findViewById(R.id.dexpCard);
        indexpCard = findViewById(R.id.indexpCard);

        sumSales = findViewById(R.id.sumSales);
        sumDirects = findViewById(R.id.sumDirect);
        sumIndirects = findViewById(R.id.sumIndirect);
        totalSales = findViewById(R.id.totalSales);
        totalDirects = findViewById(R.id.totalDirects);
        totalIndirects = findViewById(R.id.totalIndirects);
        grossProfits = findViewById(R.id.grossProfits);
        netProfits = findViewById(R.id.netProfit);

        salesCard.setOnClickListener(this);
        dexpCard.setOnClickListener(this);
        indexpCard.setOnClickListener(this);

        databaseViewModel.sumSales().observe(this, aFloat -> {
            if (aFloat == null) {
                sumSales.setText("KES 00.00");
                totalSales.setText("KES 00.00");
                tSales = 0;
            } else {
                sumSales.setText(String.format("KES %,.2f", (double) aFloat));
                totalSales.setText(String.format("KES %,.2f", (double) aFloat));
                tSales = aFloat;
            }

            runGross();
        });

        databaseViewModel.sumDirects().observe(this, aFloat -> {
            if (aFloat == null) {
                sumDirects.setText("KES 00.00");
                totalDirects.setText("KES 00.00");
                tDirects = 0;
            } else {
                sumDirects.setText(String.format("KES %,.2f", (double) aFloat));
                totalDirects.setText(String.format("KES %,.2f", (double) aFloat));
                tDirects = aFloat;
            }

            runGross();
        });

        databaseViewModel.sumIndirects().observe(this, aFloat -> {
            if (aFloat == null) {
                sumIndirects.setText("KES 00.00");
                totalIndirects.setText("KES 00.00");
                tIndirects = 0;
            } else {
                sumIndirects.setText(String.format("KES %,.2f", (double) aFloat));
                totalIndirects.setText(String.format("KES %,.2f", (double) aFloat));
                tIndirects = aFloat;
            }

            runGross();
        });
    }

    private void runGross() {
        grossProfits.setText(String.format("KES %,.2f", (double) (tSales - tDirects)));
        netProfits.setText(String.format("KES %,.2f", (double) (tSales - tDirects)));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salesCard:
                TransactionsActivity.mode = "SALES";
                break;
            case R.id.dexpCard:
                TransactionsActivity.mode = "DIRECT";
                break;
            case R.id.indexpCard:
                TransactionsActivity.mode = "INDIRECT";
                break;
        }

        Intent intent = new Intent(this, TransactionsActivity.class);
        startActivity(intent);
    }
}