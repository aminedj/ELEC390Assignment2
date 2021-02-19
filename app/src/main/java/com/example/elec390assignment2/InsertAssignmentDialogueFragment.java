package com.example.elec390assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class InsertAssignmentDialogueFragment extends DialogFragment {
    protected EditText assignmentTitleEditText;
    protected EditText gradeEditText;
    protected Button saveAssignmentButton;
    protected Button cancelAssignmentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_insert_assignment, container, false);
        assignmentTitleEditText = view.findViewById(R.id.assignmentTitleEditText);
        gradeEditText = view.findViewById(R.id.assignmentGradeEditText);

        saveAssignmentButton = view.findViewById(R.id.assSavebtn);
        cancelAssignmentButton = view.findViewById(R.id.assCancelbtn);
        DatabaseHelper db = new DatabaseHelper(getContext());
        saveAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assignmentTitleEditText.getText().toString().matches("") || gradeEditText.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(gradeEditText.getText().toString()) < 100) {
                    Bundle bundle = getArguments();
                    int id = bundle.getInt("Id");
                    Assignment newAssignment = new Assignment(assignmentTitleEditText.getText().toString(), Integer.parseInt(gradeEditText.getText().toString()), id);
                    db.addAssignment(newAssignment);
                    Toast.makeText(getContext(), "Assignment added!", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                } else {
                    Toast.makeText(getContext(), "Grade must be less than 100", Toast.LENGTH_SHORT).show();

                }
            }
        });

        cancelAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });

        return view;
    }
}
