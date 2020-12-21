package com.msc.mobileapps.mwanabiashara.db;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@TypeConverters(DateConverter.class)
@Entity(tableName = "sales")
@Keep
public class Sale {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String title;
    public String quantity;
    public float amount;
    public String comments;
    public Date createdAt;

    public Sale() {
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
