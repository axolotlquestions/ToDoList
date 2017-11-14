package com.example.alexandersmith.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Task> taskList = dbHelper.all();
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, taskList);
        ListView listView = (ListView)findViewById(R.id.taskList);
        listView.setAdapter(taskListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.add_task){
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTask(View listItem) {
        Task task = (Task) listItem.getTag();
        Intent i = new Intent(this, TaskActivity.class);
        i.putExtra("name", task.getName());
        i.putExtra("description", task.getDescription());
        i.putExtra("dueDate", task.getDueDate().getTime());
        i.putExtra("completed", task.isCompleted());
        i.putExtra("id", task.getId());
        startActivity(i);
    }
}
