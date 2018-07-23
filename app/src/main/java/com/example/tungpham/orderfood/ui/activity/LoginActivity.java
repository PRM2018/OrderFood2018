package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.model.AccountModel;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login=findViewById(R.id.btn_login);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AccountModel am=new AccountModel();
                    String acc=et_username.getText().toString();
                    String pass=et_password.getText().toString();
                    boolean checkExit=am.checkAccountAndPass(acc,pass);
                    if(checkExit==true){
                        int checkRole=am.checkRole(acc);
                        if(checkRole==1) {
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                        }
                        if(checkRole==2) {
                            Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
                            startActivity(intent);
                        }
                    }
                    if(checkExit==false){
                        Toast.makeText(getApplicationContext(),"Wrong account or pass",Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
