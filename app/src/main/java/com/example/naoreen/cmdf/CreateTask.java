package com.example.naoreen.cmdf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class CreateTask extends AppCompatActivity {
    SeekBar pnt;
    TextView val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        pnt = findViewById(R.id.point);
        val  = findViewById(R.id.pointVal);
        pnt.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressVal;
                    //TextView tv = findViewById(R.id.pointVal);
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //double value = 1 + (progress * 1);
                        progressVal = progress;
                        val.setText(progressVal);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        val.setText(progressVal);
                    }
                }
        );
    }


    public void createTask(View view){
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        //Description
        EditText desText = findViewById(R.id.des);
        String des = desText.getText().toString();

        // Point
        String pointVal = val.getText().toString();

        // Due Date
        
    }

}
