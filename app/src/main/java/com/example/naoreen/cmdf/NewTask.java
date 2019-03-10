package com.example.naoreen.cmdf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

public class NewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        //TODO: display members -- Parse JSON file to get list of users

        // display task difficulty level
        Spinner pnt = findViewById(R.id.point);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nums, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnt.setAdapter(adapter);
        pnt.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createTask(View view){


        //Description
        EditText desText = findViewById(R.id.des);
        String des = desText.getText().toString();

        // Point
        Spinner p = findViewById(R.id.point);
        String point = p.getSelectedItem().toString();

        // Due Date
        EditText dateText = findViewById(R.id.date);
        String date = dateText.getText().toString();

        // Status
        String status = "TODO";

        JSONObject task = new JSONObject();


    }

}
