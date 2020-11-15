package com.karimun.todolist.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="task_id")
    public long taskId;

    @NonNull
    @ColumnInfo(name="task_title")
    String taskTitle;

    @NonNull
    @ColumnInfo(name="timestamp")
    String timestamp;

    @ColumnInfo(name="task_group")
    int taskGroup;

    public Task(@NonNull String taskTitle, @NonNull String timestamp, int taskGroup) {
        this.taskTitle = taskTitle;
        this.timestamp = timestamp;
        this.taskGroup = taskGroup;
    }

    @NonNull
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(@NonNull String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(int taskGroup) {
        this.taskGroup = taskGroup;
    }
}
