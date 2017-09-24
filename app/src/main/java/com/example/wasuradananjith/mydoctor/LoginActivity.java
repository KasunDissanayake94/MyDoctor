package com.example.wasuradananjith.mydoctor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private TextView register;
    private EditText etUser,etPass;
    private DatabaseHelper2 db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper2(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (TextView) findViewById(R.id.btnReg);
        etUser = (EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if (session.loggedin()){
            startActivity(new Intent(LoginActivity.this,HomeScreenActivity.class));
            finish();
        }

    }


    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btnLogin: {
                DatabaseHelper2 myDb = new DatabaseHelper2(this);
                Cursor res = myDb.getPhoneNumber(etUser.getText().toString().trim(), etPass.getText().toString());
                while (res.moveToNext()) {
                    PhoneNumber.pn = res.getString(3);
                }
            }
            login();
            break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,Register.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            default:

        }
    }

    private void login(){
        String username = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (db.getUser(username,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this,LoadingHome.class));
            //startActivity(new Intent(LoadingHome.this,First.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Wrong username/password",Toast.LENGTH_SHORT).show();
        }
    }

}
