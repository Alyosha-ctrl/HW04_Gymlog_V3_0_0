package com.example.hw04_gymlog_v300.Database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hw04_gymlog_v300.Database.entities.GymLog;

@Database(entities = {GymLog.class}, version = 1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {

    public static final String gymLogTable = "gymLogTable";
}
