package com.example.minchev.dailysmarts.dataRoom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {

    @Query("SELECT * from quotelist ORDER BY id DESC")
    LiveData<List<QuoteEntity>> getAll();

    @Delete
    void delete(QuoteEntity param);

    @Insert
    long insert(QuoteEntity weatherEntity);
}
