package com.karimun.todolist.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.karimun.todolist.Adapters.TodoListTodayAdapter;
import com.karimun.todolist.TaskUtils;

// Used to send a notification when a broadcast is received (Not in use for now)
public class NotifyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TaskUtils.makeNotification(context, intent.getStringExtra(TaskUtils.TASK_TITLE_KEY));
    }
}
