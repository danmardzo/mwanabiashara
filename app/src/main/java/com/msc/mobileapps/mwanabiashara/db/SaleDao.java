package com.msc.mobileapps.mwanabiashara.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SaleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertSale(Sale sale);

    @Query("DELETE from sales where id=:localId")
    void deleteSaleByLocalId(int localId);

    @Query("SELECT * from sales")
    LiveData<List<Sale>> getSalesByDate();

    @Query("SELECT SUM(amount) from sales")
    LiveData<Float> sumSales();

    @Update
    void updateSale(Sale sale);
}
