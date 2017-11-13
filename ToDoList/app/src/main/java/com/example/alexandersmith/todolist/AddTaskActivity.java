package com.example.alexandersmith.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    EditText editName;
    EditText editDescription;
    EditText editDueDate;
    CheckBox completed;
    Boolean completedcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editName = (EditText) findViewById(R.id.NameText);
        editDescription = (EditText) findViewById(R.id.DescriptionText);
        editDueDate = (EditText) findViewById(R.id.DateText);
        completed = (CheckBox) findViewById(R.id.checkBox);
    }


     public void getCompletedCheck(View checkBox){
        if (completed.isChecked()){
            completedcheck = true;
        }
        else completedcheck = false;
    }


    public void addTask(View Button) throws ParseException {
        DBHelper dbHelper = new DBHelper(this);
        String name = editName.getText().toString();
        String description = editDescription.getText().toString();
        String dueDatestring = editDueDate.getText().toString();
        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDatestring);
        getCompletedCheck(completed);
        dbHelper.save(name, description, dueDate, completedcheck);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }




}



