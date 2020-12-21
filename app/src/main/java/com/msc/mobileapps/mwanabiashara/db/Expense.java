package com.msc.mobileapps.mwanabiashara.db;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@TypeConverters(DateConverter.class)
@Entity(tableName = "expenses")
@Keep
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String title;
    public String expenseType;
    public String quantity;
    public float amount;
    public String comments;
    public Date createdAt;

    public Expense() {
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
