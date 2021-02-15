package com.example.elec390assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class assignmentActivity extends AppCompatActivity {

    private static final String TAG = "assigmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Intent mIntent = getIntent();
        long intValue = mIntent.getLongExtra("position", 404);
        Log.d(TAG, "onCreate: " + intValue);
    }
}