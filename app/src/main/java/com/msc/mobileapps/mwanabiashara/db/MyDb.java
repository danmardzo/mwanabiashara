package com.msc.mobileapps.mwanabiashara.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Sale.class, Expense.class},
        version = 8,
        exportSchema = false
)
public abstract class MyDb extends RoomDatabase {
    public abstract SaleDao saleDao();

    public abstract ExpenseDao expenseDao();
}