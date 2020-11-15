package com.karimun.todolist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karimun.todolist.Models.Task;
import com.karimun.todolist.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoListDay5Adapter extends RecyclerView.Adapter<TodoListDay5Adapter.Day5ViewHolder>{

    List<Task> taskList;

    public TodoListDay5Adapter() {}

    @NonNull
    @Override
    public TodoListDay5Adapter.Day5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new TodoListDay5Adapter.Day5ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListDay5Adapter.Day5ViewHolder holder, int position) {
        holder.txtTaskName.setText(taskList.get(position).getTaskTitle());
        holder.txtTimestamp.setText(taskList.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        if (taskList != null && !taskList.isEmpty())
            return taskList.size();
        return 0;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public static class Day5ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTaskName;
        private TextView txtTimestamp;

        public Day5ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaskName = itemView.findViewById(R.id.txt_task_name);
            txtTimestamp = itemView.findViewById(R.id.txt_timestamp);
        }
    }
}
