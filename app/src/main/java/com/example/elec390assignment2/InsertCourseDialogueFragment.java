package com.example.elec390assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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
        courseTitleEditText = view.findViewById(R.id.courseTitleEditText);
        courseCodeEditText = view.findViewById(R.id.courseCodeEditText);

        saveCourseButton = view.findViewById(R.id.savebtn);
        cancelCourseButton = view.findViewById(R.id.cancelbtn);

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
