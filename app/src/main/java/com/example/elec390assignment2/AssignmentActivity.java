package com.example.elec390assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.elec390assignment2.fragments.InsertAssignmentDialogueFragment;
import com.example.elec390assignment2.models.Assignment;
import com.example.elec390assignment2.models.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private static final String TAG = "assigmentActivity";
    protected ListView assignmentsListView;
    protected FloatingActionButton addAssignmentFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        assignmentsListView = findViewById(R.id.assignmentListView);
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
    }

    public void loadAssignmentListView() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        int courseId = getSelectedId();
        addAssignmentFAB = findViewById(R.id.addAssignmentFAB);
        ArrayList<Assignment> assignments = db.getAllAssignment(courseId);
        ArrayAdapter<String> assignmentAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_listview, assignmentToString(assignments));
        assignmentsListView.setAdapter(assignmentAdapter);
    }

    private int getSelectedId() {
        return getIntent().getIntExtra("Id", 404);
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