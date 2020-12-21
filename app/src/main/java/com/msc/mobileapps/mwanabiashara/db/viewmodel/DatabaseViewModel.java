package com.msc.mobileapps.mwanabiashara.db.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.msc.mobileapps.mwanabiashara.db.DatabaseRepository;
import com.msc.mobileapps.mwanabiashara.db.Expense;
import com.msc.mobileapps.mwanabiashara.db.ExpenseDao;
import com.msc.mobileapps.mwanabiashara.db.Sale;
import com.msc.mobileapps.mwanabiashara.db.SaleDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseViewModel extends ViewModel {

    @Inject
    public SaleDao saleDao;
    @Inject
    public ExpenseDao expenseDao;

    private DatabaseRepository repository;

    @Inject
    public DatabaseViewModel(DatabaseRepository repository) {
        this.repository = repository;
    }

    public void updateSale(Sale sale) {
        if (sale != null) {
            repository.updateSale(sale);
        }
    }

    public void insertSale(Sale sale) {
        if (sale != null) {
            repository.insertSale( sale);
        }
    }

    public void deleteSale(Sale sale) {
        if (sale != null) {
            repository.deleteSale( sale);
        }
    }
    
    public LiveData<List<Sale>> getSales() {
        return Transformations.map(this.repository.getMonthlyExpenses(), result -> result);
    }

    public LiveData<Float> sumSales() {
        return Transformations.map(this.repository.sumSales(), result -> result);
    }

    public LiveData<Float> sumDirects() {
        return Transformations.map(this.repository.sumDirects(), result -> result);
    }

    public LiveData<Float> sumIndirects() {
        return Transformations.map(this.repository.sumIndirects(), result -> result);
    }

    public void updateExpense(Expense expense) {
        if (expense != null) {
            repository.updateExpense(expense);
        }
    }

    public void insertExpense(Expense expense) {
        if (expense != null) {
            repository.insertExpense( expense);
        }
    }

    public void deleteExpense(Expense expense) {
        if (expense != null) {
            repository.deleteExpense( expense);
        }
    }

    public LiveData<List<Expense>> getExpenses(String expenseType) {
        return Transformations.map(this.repository.getMonthlyExpenses(expenseType), result -> result);
    }
}


