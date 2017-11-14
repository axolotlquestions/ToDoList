package com.example.alexandersmith.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by alexandersmith on 10/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tasks.db";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String TASKS_COLUMN_ID = "id";
    public static final String TASKS_COLUMN_NAME = "name";
    public static final String TASKS_COLUMN_DESCRIPTION = "description";
    public static final String TASKS_COLUMN_DATE = "dueDate";
    public static final String TASKS_COLUMN_COMPLETED = "completed";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TASKS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, description TEXT, dueDate INTEGER, completed NUMERIC)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TASKS_TABLE_NAME);
        onCreate(db);
    }

    public boolean save(String name, String descripton, Date dueDate, boolean completed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASKS_COLUMN_NAME, name);
        contentValues.put(TASKS_COLUMN_DESCRIPTION, descripton);
        contentValues.put(TASKS_COLUMN_DATE, dueDate.getTime());
        contentValues.put(TASKS_COLUMN_COMPLETED, completed);
        db.insert(TASKS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Task> all() {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TASKS_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Integer id = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_DESCRIPTION));
            Long datelong = cursor.getLong(cursor.getColumnIndex(TASKS_COLUMN_DATE));
            Date dueDate = new Date(datelong);
            boolean completed = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_COMPLETED)) > 0;
            Task task = new Task(name, description, dueDate, completed, id);
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    public void delete(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(TASKS_TABLE_NAME, selection, values);
    }



}
