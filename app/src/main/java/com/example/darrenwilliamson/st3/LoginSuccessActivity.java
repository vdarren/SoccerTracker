package com.example.darrenwilliamson.st3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DarrenWilliamson on 04/04/2017.
 */


public class LoginSuccessActivity extends AppCompatActivity {


    ArrayList<HashMap<String, String>> tableList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        TextView txtname = (TextView) findViewById(R.id.txt_success_name);
        TextView txtemail = (TextView) findViewById(R.id.txt_success_email);
        Button _btnlogout = (Button) findViewById(R.id.btn_logout);
        Button _btnContinue = (Button) findViewById(R.id.btn_continue);


        Intent intent = getIntent();

        String loginName = intent.getStringExtra("fullname");
        String loginEmail = intent.getStringExtra("email");
        txtname.setText("Welcome, " + loginName);
        txtemail.setText(loginEmail);

        ArrayList<HashMap<String, String>> contactList;
        _btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccessActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(LoginSuccessActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();

                    }
                });

                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        _btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cont = new Intent(LoginSuccessActivity.this, MainHubActivity.class);
                startActivity(cont);
            }
        });
    }
}







