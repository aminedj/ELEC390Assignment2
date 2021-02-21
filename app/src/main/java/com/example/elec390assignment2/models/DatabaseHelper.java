package com.example.elec390assignment2.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elec390assignment2.models.Assignment;
import com.example.elec390assignment2.models.Course;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coursesManager";
    private static final String TABLE_COURSES = "courses";
    private static final String TABLE_ASSIGNMENT = "assignments";
    private static final String KEY_ID = "id";

    //course table columns
    private static final String KEY_COURSE_TITLE = "courseTitle";
    private static final String KEY_COURSE_CODE = "courseCode";

    //assignment table columns
    private static final String KEY_ASSIGNMENT_TITLE = "assignmentTitle";
    private static final String KEY_ASSIGNMENT_GRADE = "assignmentGrade";
    private static final String KEY_COURSE_ID = "courseId";

    //table create sql statements
    private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COURSE_TITLE + " TEXT NOT NULL," + KEY_COURSE_CODE
            + " TEXT)";
    private static final String CREATE_TABLE_ASSIGNMENTS = "CREATE TABLE " + TABLE_ASSIGNMENT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ASSIGNMENT_TITLE + " TEXT NOT NULL," + KEY_ASSIGNMENT_GRADE
            + " TEXT," + KEY_COURSE_ID + " INTEGER," + " FOREIGN KEY (" + KEY_COURSE_ID + ") REFERENCES " + TABLE_COURSES + " (" + KEY_ID + ")" + ")";
    private static final String TAG = "DBCreate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: " + CREATE_TABLE_COURSES + "\n" + CREATE_TABLE_ASSIGNMENTS);
        try {
            db.execSQL(CREATE_TABLE_COURSES);
            db.execSQL(CREATE_TABLE_ASSIGNMENTS);
        } catch (android.database.SQLException e) {
            Log.d(TAG, "onCreate: " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        onCreate(db);

    }

    public long addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_TITLE, course.getTitle());
        values.put(KEY_COURSE_CODE, course.getCode());
        long id = db.insert(TABLE_COURSES, null, values);
        db.close();
        return id;
    }

    public long addAssignment(Assignment assignment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ASSIGNMENT_TITLE, assignment.getTitle());
        values.put(KEY_ASSIGNMENT_GRADE, assignment.getGrade());
        values.put(KEY_COURSE_ID, assignment.getCourseId());
        long id = db.insert(TABLE_ASSIGNMENT, null, values);
        db.close();
        return id;
    }

    public int removeClass(int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSIGNMENT, KEY_COURSE_ID + "= ?", new String[]{String.valueOf(classId)});
        int id = db.delete(TABLE_COURSES, KEY_ID + "= ?", new String[]{String.valueOf(classId)});
        db.close();
        return id;

    }

    public ArrayList<Course> getAllClasses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSES;
        ArrayList<Course> courses = new ArrayList<Course>();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                course.setCode((c.getString(c.getColumnIndex(KEY_COURSE_CODE))));
                course.setTitle(c.getString(c.getColumnIndex(KEY_COURSE_TITLE)));

                // adding to todo list
                courses.add(course);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return courses;
    }

    public ArrayList<Assignment> getAllAssignmentsAClass(int courseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENT + " WHERE " + KEY_COURSE_ID + " = '" + courseId + "'";
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Assignment assignment = new Assignment();
                assignment.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                assignment.setGrade((c.getInt(c.getColumnIndex(KEY_ASSIGNMENT_GRADE))));
                assignment.setTitle(c.getString(c.getColumnIndex(KEY_ASSIGNMENT_TITLE)));
                assignment.setCourseId(c.getInt(c.getColumnIndex(KEY_COURSE_ID)));
                assignments.add(assignment);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return assignments;
    }

    public ArrayList<Assignment> getAllAssignmentsAverage() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENT;
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Assignment assignment = new Assignment();
                assignment.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                assignment.setGrade((c.getInt(c.getColumnIndex(KEY_ASSIGNMENT_GRADE))));
                assignment.setTitle(c.getString(c.getColumnIndex(KEY_ASSIGNMENT_TITLE)));
                assignment.setCourseId(c.getInt(c.getColumnIndex(KEY_COURSE_ID)));
                assignments.add(assignment);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return assignments;
    }

}

