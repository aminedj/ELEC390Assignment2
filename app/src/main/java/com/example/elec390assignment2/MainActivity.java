package com.example.elec390assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elec390assignment2.fragments.InsertCourseDialogueFragment;
import com.example.elec390assignment2.models.Course;
import com.example.elec390assignment2.models.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton addCourseFAB;
    protected ListView gradeListView;
    protected ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCourseFAB = findViewById(R.id.AddCourseFAB);
        gradeListView = findViewById(R.id.gradeListView);
        loadListView();

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
            String str = course.getTitle() + "\n" + course.getCode();
            strCourses.add(str);

        }

        return strCourses;
    }
}