package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button btnLogin,btnRegis;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.user);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegis = (Button) findViewById(R.id.btnRegis);



//        final SharedPreferences prefer = getSharedPreferences("MYDATA",MODE_PRIVATE);
//        final Boolean isloggedin = prefer.getBoolean("ISLOGIN",false);
//        if(isloggedin)
//        {
//            Intent main = new Intent(Login.this, MainActivity.class);
//            startActivity(main);
//        }
//        final String required_username = prefer.getString("USERNAME","DEFAULT_USERNAME");
//        final String required_password = prefer.getString("PASSWORD","DEFAULT_PASSWORD");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

//                if(user.equals(required_username)) {
////                    if (pass.equals(required_password)){
//                    Intent main = new Intent(Login.this, MainActivity.class);
//                    prefer.edit().putBoolean("ISLOGGEDIN",false).commit();
//                    startActivity(main);
//
////                    }else {
////                        Toast.makeText(Login.this,"Password is Incorect", Toast.LENGTH_SHORT).show();
////                    }
//                }else {
//                    Toast.makeText(Login.this,"Username is Incorect", Toast.LENGTH_SHORT).show();
//                }
                SharedPreferences prefer = getSharedPreferences("MYDATA",MODE_PRIVATE);
                SharedPreferences.Editor edit = prefer.edit();

//                String userdetail = prefer.getString(user + "data","Username is Incorect");
//                String passdetail = prefer.getString(pass + "data","Password is Incorect");
//                String userdetail = prefer.getString(user ,"");
//                String passdetail = prefer.getString(pass ,"");
//                edit.putString("user",userdetail);
//                edit.putString("pass",passdetail);
                String userdetail = prefer.getString(user + pass + "data","Username or Password is Incorect");
                edit.putString("display",userdetail);
                edit.commit();

                Intent iMain = new Intent(Login.this,MainActivity.class);
                startActivity(iMain);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegis = new Intent(Login.this,Register.class);
                startActivity(iRegis);
                finish();
            }
        });
    }
}
