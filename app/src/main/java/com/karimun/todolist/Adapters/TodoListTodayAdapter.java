package com.karimun.todolist.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karimun.todolist.TaskUtils;
import com.karimun.todolist.Models.Task;
import com.karimun.todolist.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoListTodayAdapter extends RecyclerView.Adapter<TodoListTodayAdapter.TodayViewHolder> {

    List<Task> taskList;

    public TodoListTodayAdapter() {}

    @NonNull
    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new TodayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayViewHolder holder, int position) {
        holder.txtTaskName.setText(taskList.get(position).getTaskTitle());
        holder.txtTimestamp.setText(taskList.get(position).getTimestamp());

        String dateNow = TaskUtils.currentDate();

        if (dateNow!=null && holder.txtTimestamp.getText().toString().compareTo(dateNow) < 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else if (dateNow!=null && holder.txtTimestamp.getText().toString().compareTo(dateNow) == 0) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        if (taskList!=null && !taskList.isEmpty())
            return taskList.size();
        return 0;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public static class TodayViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTaskName;
        private TextView txtTimestamp;

        public TodayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaskName = itemView.findViewById(R.id.txt_task_name);
            txtTimestamp = itemView.findViewById(R.id.txt_timestamp);
        }
    }
}
