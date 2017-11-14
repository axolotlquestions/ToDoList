package com.example.alexandersmith.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alexandersmith on 10/11/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }
        Task currentTask = getItem(position);
        TextView name = (TextView)listItemView.findViewById(R.id.nameView);
        name.setText(currentTask.getName());
        TextView date = (TextView)listItemView.findViewById(R.id.dueDateView);
        date.setText(toDate(currentTask.getDueDate().getTime()));
        listItemView.setTag(currentTask);

        return listItemView;
    }

    private String toDate(long timestamp) {
        Date date = new Date (timestamp);
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }



}

