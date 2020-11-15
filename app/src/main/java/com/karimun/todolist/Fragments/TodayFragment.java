package com.karimun.todolist.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.karimun.todolist.Adapters.TodoListTodayAdapter;
import com.karimun.todolist.Database.TaskViewModel;
import com.karimun.todolist.MainActivity;
import com.karimun.todolist.Models.Task;
import com.karimun.todolist.R;
import com.karimun.todolist.databinding.FragmentTodayBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TodayFragment extends Fragment {
    //private static final String TAG = TodayFragment.class.getSimpleName();
    public static final String CURRENT_DATE_FILE ="current_date_file";
    public static final String CURRENT_DATE_KEY = "current_date";

    FragmentTodayBinding binding;
    TodoListTodayAdapter todoListTodayAdapter;
    public static TaskViewModel taskViewModel;

    String currentDateLive, currentDate;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTodayBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = view.getContext().getSharedPreferences(CURRENT_DATE_FILE, Context.MODE_PRIVATE);
        editor= preferences.edit();

        todoListTodayAdapter = new TodoListTodayAdapter();
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        binding.rvTodoListToday.setAdapter(todoListTodayAdapter);
        binding.rvTodoListToday.setLayoutManager(new LinearLayoutManager(view.getContext()));

        binding.btnAddTaskToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddTask(view);
            }
        });

        currentDateLive = MainActivity.setupDateOnTabs()[0];
        currentDate = preferences.getString(CURRENT_DATE_KEY, null);

        if (currentDate!= null) {
            if (!currentDate.equals(currentDateLive)) {
                //Log.i(TAG, "REACHED HERE!!");
                taskViewModel.updateTodayList();
                taskViewModel.updateTomorrowList();
                taskViewModel.updateDay3List();
                taskViewModel.updateDay4List();
                taskViewModel.updateDay5List();
                taskViewModel.updateDay6List();
                taskViewModel.updateDay7List();
                editor.putString(CURRENT_DATE_KEY, currentDateLive);
                currentDate = currentDateLive;
            }
        }
        else {
            //Log.i(TAG, "REACHED HERE 2!!");
            editor.putString(CURRENT_DATE_KEY, currentDateLive);
            currentDate = currentDateLive;
        }
        editor.apply();

        taskViewModel.getTodayTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (!tasks.isEmpty()){
                    binding.txtNoTasksLabel.setVisibility(View.GONE);
                }
                else{
                    binding.txtNoTasksLabel.setVisibility(View.VISIBLE);
                }
                todoListTodayAdapter.setTaskList(tasks);
            }
        });
    }

    // Trigger the displayTaskSetupWindow function
    public void actionAddTask(View view) {
        displayTaskSetupWindow(view.getContext());
    }

    // Display task form dialog for the user to fill in
    private void displayTaskSetupWindow(final Context context) {
        final Dialog taskSetupDialog = new Dialog(context);
        taskSetupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        taskSetupDialog.setContentView(R.layout.task_setup_dialog);
        taskSetupDialog.show();

        final EditText etEnterTaskTitle = taskSetupDialog.findViewById(R.id.et_enter_task_title);
        final EditText etEnterTime = taskSetupDialog.findViewById(R.id.et_enter_time);
        Button btnNewTaskSubmit = taskSetupDialog.findViewById(R.id.btn_new_task_submit);

        if (btnNewTaskSubmit!=null && etEnterTaskTitle!=null && etEnterTime!=null) {

            btnNewTaskSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(etEnterTaskTitle.getText()) && !TextUtils.isEmpty(etEnterTime.getText())) {
                        taskViewModel.insertTask(new Task(etEnterTaskTitle.getText().toString(), etEnterTime.getText().toString(), 0));
                        taskSetupDialog.cancel();
                    }
                    else {
                        Toast.makeText(view.getContext(), "Please specify Task Title and Time.", Toast.LENGTH_LONG).show();
                    }
                }
            });

            etEnterTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);

                    final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                    TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            try {
                                c.setTime(dateFormat.parse(String.format("%s:%s", i, i1)));
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }

                            etEnterTime.setText(dateFormat.format(c.getTime()));
                        }
                    }, hour, minute, true);


                    timePickerDialog.show();
                }
            });
        }
        else{
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_LONG).show();
        }
    }
}