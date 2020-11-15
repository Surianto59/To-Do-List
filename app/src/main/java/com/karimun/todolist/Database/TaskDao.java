package com.karimun.todolist.Database;

import com.karimun.todolist.Models.Task;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(Task task);

    @Query("SELECT * FROM task WHERE task_group = 0 ORDER BY timestamp ASC")
    LiveData<List<Task>> getTodayTasks();

    @Query("SELECT * FROM task WHERE task_group = 1 ORDER BY timestamp ASC")
    LiveData<List<Task>> getTomorrowTasks();

    @Query("SELECT * FROM task WHERE task_group = 2 ORDER BY timestamp ASC")
    LiveData<List<Task>> getDay3Tasks();

    @Query("SELECT * FROM task WHERE task_group = 3 ORDER BY timestamp ASC")
    LiveData<List<Task>> getDay4Tasks();

    @Query("SELECT * FROM task WHERE task_group = 4 ORDER BY timestamp ASC")
    LiveData<List<Task>> getDay5Tasks();

    @Query("SELECT * FROM task WHERE task_group = 5 ORDER BY timestamp ASC")
    LiveData<List<Task>> getDay6Tasks();

    @Query("SELECT * FROM task WHERE task_group = 6 ORDER BY timestamp ASC")
    LiveData<List<Task>> getDay7Tasks();

    @Query("UPDATE task SET task_group = 0 WHERE task_group = 1")
    void updateTodayTasks();

    @Query("UPDATE task SET task_group = 1 WHERE task_group = 2")
    void updateTomorrowTasks();

    @Query("UPDATE task SET task_group = 2 WHERE task_group = 3")
    void updateDay3Tasks();

    @Query("UPDATE task SET task_group = 3 WHERE task_group = 4")
    void updateDay4Tasks();

    @Query("UPDATE task SET task_group = 4 WHERE task_group = 5")
    void updateDay5Tasks();

    @Query("UPDATE task SET task_group = 5 WHERE task_group = 6")
    void updateDay6Tasks();

    @Query("DELETE FROM task WHERE task_group = 6")
    void updateDay7Tasks();

    @Query("DELETE FROM task WHERE task_group=0")
    void removeTodayTasks();

    @Query("DELETE FROM task WHERE task_group=1")
    void removeTomorrowTasks();

    @Query("DELETE FROM task WHERE task_group=2")
    void removeDay3Tasks();

    @Query("DELETE FROM task WHERE task_group=3")
    void removeDay4Tasks();

    @Query("DELETE FROM task WHERE task_group=4")
    void removeDay5Tasks();

    @Query("DELETE FROM task WHERE task_group=5")
    void removeDay6Tasks();

    @Query("DELETE FROM task WHERE task_group=6")
    void removeDay7Tasks();

    @Query("DELETE FROM task")
    void removeAllTasks();
}
