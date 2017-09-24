package com.example.wasuradananjith.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button reg;
    private TextView tvLogin;
    private EditText etUser,etPass,etPhoneNo;
    private  DatabaseHelper2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper2(this);
        reg = (Button)findViewById(R.id.btnReg2);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etUser = (EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPassword);
        etPhoneNo = (EditText)findViewById(R.id.etPhone);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btnReg2:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(Register.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            default:

        }
    }

    private void register(){
        String username = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String phone = etPhoneNo.getText().toString().trim();
        if(username.isEmpty() || pass.isEmpty() || phone.isEmpty()){
            Toast.makeText(getApplicationContext(),"Username/password/phone field empty", Toast.LENGTH_SHORT).show();
        }
        else{
            db.addUser(username,pass,phone);
            Toast.makeText(getApplicationContext(),"User registered", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(Register.this,LoadingRegister.class));
        }
    }
}
