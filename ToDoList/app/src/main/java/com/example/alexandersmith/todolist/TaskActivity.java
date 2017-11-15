package com.example.alexandersmith.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {

    TextView nameView;
    TextView descriptionView;
    TextView dateview;
    CheckBox CompletedBox;
    Bundle extras;
    Boolean completedcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        extras = getIntent().getExtras();
        String name = extras.getString("name");
        String description = extras.getString("description");
        Long datelong = extras.getLong("dueDate");
        Boolean completed = extras.getBoolean("completed");

        nameView = (TextView)findViewById(R.id.NameView);
        nameView.setText("Task Name: " + name);
        descriptionView = (TextView)findViewById(R.id.DescriptionView);
        descriptionView.setText("Task Description: " + description);
        dateview = (TextView)findViewById(R.id.DateView);
        dateview.setText("Due date: " + toDate(datelong));
        CompletedBox = (CheckBox)findViewById(R.id.CompletedBox);
        CompletedBox.setChecked(completed);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_task, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.delete_task){
            int id = extras.getInt("id");
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.delete(id);
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private String toDate(long timestamp) {
        Date date = new Date (timestamp);
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void getCompletedCheck(View checkBox){
        if (CompletedBox.isChecked()){
            completedcheck = true;
        }
        else completedcheck = false;
    }

    public void checkBoxchange(View checkbox) {
        int id = extras.getInt("id");
        getCompletedCheck(CompletedBox);
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.updateCompleted(id, completedcheck);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }






}
