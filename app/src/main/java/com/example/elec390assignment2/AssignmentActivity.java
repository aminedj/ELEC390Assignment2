package com.example.elec390assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.elec390assignment2.fragments.InsertAssignmentDialogueFragment;
import com.example.elec390assignment2.models.Assignment;
import com.example.elec390assignment2.models.Course;
import com.example.elec390assignment2.models.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private static final String TAG = "assigmentActivity";
    protected ListView assignmentsListView;
    protected FloatingActionButton addAssignmentFAB;
    protected Button deleteCourseBtn;
    protected TextView courseNameCodeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.assignemntToolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        assignmentsListView = findViewById(R.id.assignmentListView);
        courseNameCodeTV = findViewById(R.id.courseNameCodetextView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        InsertAssignmentDialogueFragment dialog = new InsertAssignmentDialogueFragment();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loadAssignmentListView();
        addAssignmentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("Id", getSelectedId());
                dialog.setArguments(b);
                fragmentTransaction.add(dialog.getId(), dialog).commit();
//                dialog.show(fragmentManager, "InsertCourseFragment");
            }
        });
        deleteCourseBtn = findViewById(R.id.deleteCoursebtn);
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.removeClass(getSelectedId());
                finish();

            }
        });
    }

    public void loadAssignmentListView() {
        int courseId = getSelectedId();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Course course = db.getAllClasses().get(getSelectedPos());
        courseNameCodeTV.setText(course.getCode() + " " + course.getTitle());
        ArrayList<Assignment> assignments = db.getAllAssignmentsAClass(courseId);
        db.close();
        addAssignmentFAB = findViewById(R.id.addAssignmentFAB);
        ArrayAdapter<String> assignmentAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_listview, assignmentToString(assignments));
        assignmentsListView.setAdapter(assignmentAdapter);
    }

    private int getSelectedId() {
        return getIntent().getIntExtra("Id", 404);
    }

    private int getSelectedPos() {
        return getIntent().getIntExtra("pos", 404);
    }

    private ArrayList<String> assignmentToString(ArrayList<Assignment> assignements) {
        ArrayList<String> strAssignments = new ArrayList<String>();
        for (Assignment assignement : assignements
        ) {
            String str = assignement.getTitle() + "\n" + assignement.getGrade();
            strAssignments.add(str);

        }
        return strAssignments;
    }
}