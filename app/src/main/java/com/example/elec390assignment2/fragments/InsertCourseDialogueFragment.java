package com.example.elec390assignment2.fragments;

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

import com.example.elec390assignment2.MainActivity;
import com.example.elec390assignment2.R;
import com.example.elec390assignment2.models.Course;
import com.example.elec390assignment2.models.DatabaseHelper;

public class InsertCourseDialogueFragment extends DialogFragment {
    protected EditText courseTitleEditText;
    protected EditText courseCodeEditText;
    protected Button saveCourseButton;
    protected Button cancelCourseButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_insert_course, container, false);
        courseTitleEditText = view.findViewById(R.id.assignmentTitleEditText);
        courseCodeEditText = view.findViewById(R.id.assignmentGradeEditText);

        saveCourseButton = view.findViewById(R.id.assSavebtn);
        cancelCourseButton = view.findViewById(R.id.assCancelbtn);
        DatabaseHelper db = new DatabaseHelper(getContext());

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courseTitleEditText.getText().toString().matches("") || courseCodeEditText.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
                } else {
                    Course newCourse = new Course(courseTitleEditText.getText().toString(), courseCodeEditText.getText().toString());
                    db.addCourse(newCourse);
                    Toast.makeText(getContext(), "Course added!", Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).loadListView();
                    getDialog().dismiss();

                }
            }
        });

        cancelCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });

        return view;
    }
}
