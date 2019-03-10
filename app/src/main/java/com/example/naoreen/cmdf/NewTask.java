package com.example.naoreen.cmdf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public ArrayList<JSONObject> allTasks;
    public ArrayList<String> members;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        members = new ArrayList<>();
        members.add("Alice");
        members.add("Bob");


        //TODO: display members -- Parse JSON file to get list of users
        Spinner usr = findViewById(R.id.member);
        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, members);
        //parse JSON file
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usr.setAdapter(adapter0);
        usr.setOnItemSelectedListener(this);



        // display task difficulty level
        Spinner pnt = findViewById(R.id.point);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nums, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnt.setAdapter(adapter);
        pnt.setOnItemSelectedListener(this);

        allTasks = new ArrayList<>();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

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

        //Member
        Spinner m = findViewById(R.id.member);
        String mem = m.getSelectedItem().toString();


        JSONObject task = new JSONObject();
        try{
            task.put("description", des);
            task.put("point", point);
            task.put("dueDate", date);
            task.put("status", "TODO");
            task.put("member", mem);
        } catch (Exception e){
            e.printStackTrace();
        }

        allTasks.add(task);


    }

}
