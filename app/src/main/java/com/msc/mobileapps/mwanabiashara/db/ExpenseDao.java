package com.msc.mobileapps.mwanabiashara.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertExpense(Expense expense);

    @Query("DELETE from expenses where id=:localId")
    void deleteExpensesByLocalId(int localId);

    @Query("SELECT * from expenses where expenseType=:expenseType")
    LiveData<List<Expense>> getExpensesByDate(String expenseType);

    @Query("SELECT SUM(amount) from expenses where expenseType = 'DIRECT'")
    LiveData<Float> sumDirects();

    @Query("SELECT SUM(amount) from expenses where expenseType = 'INDIRECT'")
    LiveData<Float> sumIndirects();

    @Update
    void updateExpense(Expense expense);
}
