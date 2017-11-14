package com.example.alexandersmith.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    Button deleteButton;
    Bundle extras;

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
        nameView.setText(name);
        descriptionView = (TextView)findViewById(R.id.DescriptionView);
        descriptionView.setText(description);
        dateview = (TextView)findViewById(R.id.DateView);
        dateview.setText(toDate(datelong));
        CompletedBox = (CheckBox)findViewById(R.id.CompletedBox);
        CompletedBox.setChecked(completed);

    }

    public void deleteTask(View button){
        int id = extras.getInt("id");
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.delete(id);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    private String toDate(long timestamp) {
        Date date = new Date (timestamp);
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }


}
