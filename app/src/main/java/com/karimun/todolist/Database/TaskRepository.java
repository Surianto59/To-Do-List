package com.karimun.todolist.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.karimun.todolist.Models.Task;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TaskRepository {
    LiveData<List<Task>> todayTaskList, tomorrowTaskList, day3TaskList, day4TaskList, day5TaskList, day6TaskList, day7TaskList;
    TaskDao taskDao;

    public TaskRepository(Context context) {
        TaskRoomDatabase database = TaskRoomDatabase.getInstance(context);
        taskDao = database.taskDao();

        todayTaskList = taskDao.getTodayTasks();
        tomorrowTaskList = taskDao.getTomorrowTasks();
        day3TaskList = taskDao.getDay3Tasks();
        day4TaskList = taskDao.getDay4Tasks();
        day5TaskList = taskDao.getDay5Tasks();
        day6TaskList = taskDao.getDay6Tasks();
        day7TaskList = taskDao.getDay7Tasks();
    }

    public LiveData<List<Task>> getTodayTaskList() {
        return todayTaskList;
    }

    public LiveData<List<Task>> getTomorrowTaskList() {
        return tomorrowTaskList;
    }

    public LiveData<List<Task>> getDay3TaskList() {
        return day3TaskList;
    }

    public LiveData<List<Task>> getDay4TaskList() {
        return day4TaskList;
    }

    public LiveData<List<Task>> getDay5TaskList() {
        return day5TaskList;
    }

    public LiveData<List<Task>> getDay6TaskList() {
        return day6TaskList;
    }

    public LiveData<List<Task>> getDay7TaskList() {
        return day7TaskList;
    }

    public void insert(Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        TaskDao taskDao;

        insertAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insertTask(tasks[0]);
            return null;
        }
    }

    public void updateTodayTasks() {
        new updateTodayAsyncTask(taskDao).execute();
    }

    private static class updateTodayAsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateTodayAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateTodayTasks();
            return null;
        }
    }

    public void updateTomorrowTasks() {
        new updateTomorrowAsyncTask(taskDao).execute();
    }

    private static class updateTomorrowAsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateTomorrowAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateTomorrowTasks();
            return null;
        }
    }

    public void updateDay3Tasks() {
        new updateDay3AsyncTask(taskDao).execute();
    }

    private static class updateDay3AsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateDay3AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateDay3Tasks();
            return null;
        }
    }

    public void updateDay4Tasks() {
        new updateDay4AsyncTask(taskDao).execute();
    }

    private static class updateDay4AsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateDay4AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateDay4Tasks();
            return null;
        }
    }

    public void updateDay5Tasks() {
        new updateDay5AsyncTask(taskDao).execute();
    }

    private static class updateDay5AsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateDay5AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateDay5Tasks();
            return null;
        }
    }

    public void updateDay6Tasks() {
        new updateDay6AsyncTask(taskDao).execute();
    }

    private static class updateDay6AsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateDay6AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateDay6Tasks();
            return null;
        }
    }

    public void updateDay7Tasks() {
        new updateDay7AsyncTask(taskDao).execute();
    }

    private static class updateDay7AsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        updateDay7AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.updateDay7Tasks();
            return null;
        }
    }

    public void removeTodayTasks() {
        new removeTodayAsyncTask(taskDao).execute();
    }

    private static class removeTodayAsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeTodayAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeTodayTasks();
            return null;
        }
    }

    public void removeTomorrowTasks() {
        new removeTomorrowAsyncTask(taskDao).execute();
    }

    private static class removeTomorrowAsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeTomorrowAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeTomorrowTasks();
            return null;
        }
    }

    public void removeDay3Tasks() {
        new removeDay3AsyncTask(taskDao).execute();
    }

    private static class removeDay3AsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeDay3AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeDay3Tasks();
            return null;
        }
    }

    public void removeDay4Tasks() {
        new removeDay4AsyncTask(taskDao).execute();
    }

    private static class removeDay4AsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeDay4AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeDay4Tasks();
            return null;
        }
    }

    public void removeDay5Tasks() {
        new removeDay5AsyncTask(taskDao).execute();
    }

    private static class removeDay5AsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeDay5AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeDay5Tasks();
            return null;
        }
    }

    public void removeDay6Tasks() {
        new removeDay6AsyncTask(taskDao).execute();
    }

    private static class removeDay6AsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeDay6AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeDay6Tasks();
            return null;
        }
    }

    public void removeDay7Tasks() {
        new removeDay7AsyncTask(taskDao).execute();
    }

    private static class removeDay7AsyncTask extends AsyncTask<Void, Void, Void> {

        TaskDao taskDao;

        removeDay7AsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeDay7Tasks();
            return null;
        }
    }

    public void removeAllTasks() {
        new removeAllAsyncTask(taskDao).execute();
    }

    private static class removeAllAsyncTask extends AsyncTask<Void, Void, Void> {
        TaskDao taskDao;

        removeAllAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.removeAllTasks();
            return null;
        }
    }
}