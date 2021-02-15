package com.example.elec390assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton addCourseFAB;
    protected ListView gradeListView;
    protected ArrayAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCourseFAB = findViewById(R.id.AddCourseFAB);
        gradeListView = findViewById(R.id.gradeListView);
        String[] testing = {"test","test2","test3","test4"};
        courseAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, testing);
        gradeListView.setAdapter(courseAdapter);

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
                Intent intent = new Intent(getApplicationContext(), assignmentActivity.class);
                intent.putExtra("position",id);
                startActivity(intent);
            }
        });
    }
}