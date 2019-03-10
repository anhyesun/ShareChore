package com.example.naoreen.cmdf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;



public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP=0;


    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.create_account) TextView _createAccount;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                login();
            }
        });

        _createAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                // start sign up activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    //TODO: figure out login logic
    public void login(){
        Log.d(TAG, "Login");
    }

}
