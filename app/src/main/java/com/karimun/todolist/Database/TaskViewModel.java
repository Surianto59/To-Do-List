package com.karimun.todolist.Database;

import android.app.Application;

import com.karimun.todolist.Models.Task;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

// Provide methods to interact with the database
public class TaskViewModel extends AndroidViewModel {
    TaskRepository repository;
    LiveData<List<Task>> todayTasks, tomorrowTasks, day3Tasks, day4Tasks, day5Tasks, day6Tasks, day7Tasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        todayTasks = repository.getTodayTaskList();
        tomorrowTasks = repository.getTomorrowTaskList();
        day3Tasks = repository.getDay3TaskList();
        day4Tasks = repository.getDay4TaskList();
        day5Tasks = repository.getDay5TaskList();
        day6Tasks = repository.getDay6TaskList();
        day7Tasks = repository.getDay7TaskList();
    }

    public LiveData<List<Task>> getTodayTasks() {
        return todayTasks;
    }

    public LiveData<List<Task>> getTomorrowTasks() {
        return tomorrowTasks;
    }

    public LiveData<List<Task>> getDay3Tasks() {
        return day3Tasks;
    }

    public LiveData<List<Task>> getDay4Tasks() {
        return day4Tasks;
    }

    public LiveData<List<Task>> getDay5Tasks() {
        return day5Tasks;
    }

    public LiveData<List<Task>> getDay6Tasks() {
        return day6Tasks;
    }

    public LiveData<List<Task>> getDay7Tasks() {
        return day7Tasks;
    }

    public void insertTask(Task task) {
        repository.insert(task);
    }

    public void updateTodayList() {
        repository.removeTodayTasks();
        repository.updateTodayTasks();
    }

    public void updateTomorrowList() {
        repository.updateTomorrowTasks();
    }

    public void updateDay3List() {
        repository.updateDay3Tasks();
    }

    public void updateDay4List() {
        repository.updateDay4Tasks();
    }

    public void updateDay5List() {
        repository.updateDay5Tasks();
    }

    public void updateDay6List() {
        repository.updateDay6Tasks();
    }

    public void updateDay7List() {
        repository.updateDay7Tasks();
    }

    public void removeTodayList() {
        repository.removeTodayTasks();
    }

    public void removeTomorrowList() {
        repository.removeTomorrowTasks();
    }

    public void removeDay3List() {
        repository.removeDay3Tasks();
    }

    public void removeDay4List() {
        repository.removeDay4Tasks();
    }

    public void removeDay5List() {
        repository.removeDay5Tasks();
    }

    public void removeDay6List() {
        repository.removeDay6Tasks();
    }

    public void removeDay7List() {
        repository.removeDay7Tasks();
    }

    public void removeAllLists() {
        repository.removeAllTasks();
    }
}
