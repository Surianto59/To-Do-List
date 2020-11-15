package com.karimun.todolist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karimun.todolist.Models.Task;
import com.karimun.todolist.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoListTomorrowAdapter extends RecyclerView.Adapter<TodoListTomorrowAdapter.TomorrowViewHolder> {

    List<Task> taskList;

    public TodoListTomorrowAdapter() {}

    @NonNull
    @Override
    public TodoListTomorrowAdapter.TomorrowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new TomorrowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListTomorrowAdapter.TomorrowViewHolder holder, int position) {
        holder.txtTaskName.setText(taskList.get(position).getTaskTitle());
        holder.txtTimestamp.setText(taskList.get(position).getTimestamp());
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

    static class TomorrowViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTaskName;
        private TextView txtTimestamp;

        public TomorrowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaskName = itemView.findViewById(R.id.txt_task_name);
            txtTimestamp = itemView.findViewById(R.id.txt_timestamp);
        }
    }
}
