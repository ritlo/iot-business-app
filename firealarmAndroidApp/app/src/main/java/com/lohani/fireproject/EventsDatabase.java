package com.lohani.fireproject;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity
public class EventsDatabase {
        @PrimaryKey
        public int uid;
        @ColumnInfo(name = "event_type")
        public String event;
        @ColumnInfo(name = "date_time")
        public String datetime;

        public void setEvent(String event, String datetime){
                this.event = event;
                this.datetime=datetime;
        }
}

