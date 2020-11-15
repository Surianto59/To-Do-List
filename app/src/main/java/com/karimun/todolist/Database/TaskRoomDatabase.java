package com.karimun.todolist.Database;

import android.content.Context;

import com.karimun.todolist.Models.Task;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Task.class}, version=1, exportSchema=false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static TaskRoomDatabase instance;

    public static TaskRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (TaskRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, TaskRoomDatabase.class, "todo_list_database").build();
                }
            }
        }
        return instance;
    }
}
