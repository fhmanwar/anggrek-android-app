package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profil extends AppCompatActivity {

    private EditText username,password;
    private Button btnupdate, btncancel, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        btnupdate = (Button) findViewById(R.id.btn_update);
        btncancel = (Button)findViewById(R.id.btn_cancel);
        btnLogout = (Button)findViewById(R.id.btnOut);

        SharedPreferences preferences = getSharedPreferences("MYDATA", MODE_PRIVATE);
//        String display = preferences.getString("display", "");
        String showUser = preferences.getString("user", "");
        String showPass = preferences.getString("pass", "");

        username.setText(showUser);
        password.setText(showPass);


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefer = getSharedPreferences("MYDATA",MODE_PRIVATE);

                String newUser = username.getText().toString();
                String newPass = password.getText().toString();

                SharedPreferences.Editor edit = prefer.edit();
                edit.putString("user",newUser);
                edit.putString("pass",newPass);
                edit.putString(newUser + newPass + "data", newUser + "\n");
                edit.commit();

                Intent iLogin = new Intent(Profil.this,MainActivity.class);
                startActivity(iLogin);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MYDATA", MODE_PRIVATE);

                String showUser = preferences.getString("user", "");
                String showPass = preferences.getString("pass", "");
                username.setText(showUser);
                password.setText(showPass);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences prefer = getSharedPreferences("MYDATA",MODE_PRIVATE);
//                SharedPreferences.Editor edit = prefer.edit();
//                edit.clear();
//                edit.apply();

                Intent iLogOut = new Intent(Profil.this, Login.class);
                iLogOut.setFlags(iLogOut.FLAG_ACTIVITY_NEW_TASK | iLogOut.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(iLogOut);
            }
        });

    }
}
