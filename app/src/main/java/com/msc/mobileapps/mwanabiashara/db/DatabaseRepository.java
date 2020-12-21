/*
  Copyright © 2018-present, Daniel Mutiso.
  All rights reserved.
  Redistribution and use in source and binary forms, with or without modification,
  are not permitted without express written permission.
 */
package com.msc.mobileapps.mwanabiashara.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.msc.mobileapps.mwanabiashara.db.Expense;
import com.msc.mobileapps.mwanabiashara.db.ExpenseDao;
import com.msc.mobileapps.mwanabiashara.db.Sale;
import com.msc.mobileapps.mwanabiashara.db.SaleDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseRepository {
    private SaleDao saleDao;
    private ExpenseDao expenseDao;

    @Inject
    DatabaseRepository(
            SaleDao saleDao,
            ExpenseDao expenseDao
    ) {
        this.saleDao = saleDao;
        this.expenseDao = expenseDao;
    }

    public void insertSale(Sale sale) {
        AsyncTask.execute(() -> {
            saleDao.insertSale(sale);
        });
    }

    public void updateSale(Sale sale) {
        AsyncTask.execute(() -> {
            saleDao.updateSale(sale);
        });
    }

    public void deleteSale(Sale sale) {
        AsyncTask.execute(() -> {
            saleDao.deleteSaleByLocalId(sale.id);
        });
    }

    public LiveData<List<Sale>> getMonthlyExpenses() {
        return Transformations.map(saleDao.getSalesByDate(), result -> result);
    }

    public LiveData<Float> sumSales() {
        return Transformations.map(saleDao.sumSales(), result -> result);
    }

    public LiveData<Float> sumDirects() {
        return Transformations.map(expenseDao.sumDirects(), result -> result);
    }

    public LiveData<Float> sumIndirects() {
        return Transformations.map(expenseDao.sumIndirects(), result -> result);
    }

    public void insertExpense(Expense expense) {
        AsyncTask.execute(() -> {
            expenseDao.insertExpense(expense);
        });
    }

    public void updateExpense(Expense expense) {
        AsyncTask.execute(() -> {
            expenseDao.updateExpense(expense);
        });
    }

    public void deleteExpense(Expense expense) {
        AsyncTask.execute(() -> {
            expenseDao.deleteExpensesByLocalId(expense.id);
        });
    }

    public LiveData<List<Expense>> getMonthlyExpenses(String expenseType) {
        return Transformations.map(expenseDao.getExpensesByDate(expenseType), result -> result);
    }


}

