package com.example.elec390assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elec390assignment2.fragments.InsertCourseDialogueFragment;
import com.example.elec390assignment2.models.Assignment;
import com.example.elec390assignment2.models.Course;
import com.example.elec390assignment2.models.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton addCourseFAB;
    protected ListView gradeListView;
    protected ArrayList<Course> courses;
    protected TextView allClassAverageTV;

    @Override
    protected void onResume() {
        super.onResume();
        loadListView();
        allClassAverageTV.setText("Average of all Assignments: " + allClassAverages());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCourseFAB = findViewById(R.id.AddCourseFAB);
        gradeListView = findViewById(R.id.gradeListView);
        allClassAverageTV = findViewById(R.id.allClassAverageTextView);

        addCourseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertCourseDialogueFragment dialog = new InsertCourseDialogueFragment();
                dialog.show(getSupportFragmentManager(), "InsertCourseFragment");
            }
        });
        gradeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AssignmentActivity.class);
                intent.putExtra("Id", courses.get(position).getId());
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });
    }

    public void loadListView() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());
        courses = db.getAllClasses();
        db.close();
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, coursesToString(courses));
        gradeListView.setAdapter(courseAdapter);
    }

    protected ArrayList<String> coursesToString(ArrayList<Course> courses) {
        ArrayList<String> strCourses = new ArrayList<String>();

        for (Course course : courses
        ) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db = new DatabaseHelper(getApplicationContext());
            ArrayList<Assignment> assignments = db.getAllAssignmentsAClass(course.getId());
            int average = 0;
            String strAverage;
            if (assignments.isEmpty()) {
                strAverage = "N/A";
            } else {
                for (Assignment assignment :
                        assignments) {
                    average += assignment.getGrade();
                }
                average /= assignments.size();
                strAverage = String.valueOf(average);
            }

            String str = course.getTitle() + "\n" + course.getCode() + "\n Assignment Average: " + strAverage;
            strCourses.add(str);

        }

        return strCourses;
    }

    protected String allClassAverages() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());
        ArrayList<Assignment> assignments = db.getAllAssignmentsAverage();
        String strAverage;
        int average = 0;
        if (assignments.isEmpty()) {
            strAverage = "N/A";
        } else {
            for (Assignment assignment :
                    assignments) {
                average += assignment.getGrade();
            }
            average /= assignments.size();
            strAverage = String.valueOf(average) + "%";


        }
            return strAverage;
    }
}