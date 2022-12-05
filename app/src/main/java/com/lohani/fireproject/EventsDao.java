package com.lohani.fireproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventsDao {
    @Query("SELECT * FROM eventsdatabase")
    List<EventsDatabase> getAll();

    @Query("SELECT * FROM eventsdatabase WHERE uid IN (:userIds)")
    List<EventsDatabase> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(EventsDatabase... data);

    @Delete
    void delete(EventsDatabase data);
}
