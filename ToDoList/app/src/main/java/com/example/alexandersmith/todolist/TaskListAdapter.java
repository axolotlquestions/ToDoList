package com.example.alexandersmith.todolist;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        date.setText(currentTask.getDueDate().toString());

        listItemView.setTag(currentTask);

        return listItemView;
    }



}

