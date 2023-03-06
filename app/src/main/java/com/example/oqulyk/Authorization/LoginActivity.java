package com.example.oqulyk.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oqulyk.DBHelper;
import com.example.oqulyk.HomeActivity;
import com.example.oqulyk.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    TextView noAccountTv,forgetIv;
    ImageView back_to_login;
    EditText emailEt,passwordEt;
    Button loginBtn;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDatabase=new DBHelper(this);

        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        loginBtn=findViewById(R.id.loginBtn);
        noAccountTv=findViewById(R.id.noAccountTv);
        forgetIv=findViewById(R.id.forgotIv);

        loginBtn.setOnClickListener(v -> {
                attempt_login();
        });
        noAccountTv.setOnClickListener(v ->  {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
        forgetIv.setOnClickListener(v ->  {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
        });

        back_to_login=findViewById(R.id.backBtn);
        back_to_login.setOnClickListener(v ->  {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
        });

    }

    private void attempt_login() {
        String email=emailEt.getText().toString();
        String password=passwordEt.getText().toString();

        if(!isEmailValid(email)) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Habar")
                    .setContentText("@ qoiudy ūmytyp kettıñız")
                    .setConfirmText("OK")
                    .show();
        }
        else if(!isPasswordValid(password)) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Habar")
                    .setContentText("Qūpia sözıñız 8 den köp boluy kerek")
                    .setConfirmText("OK")
                    .show();
        }

        Cursor res = myDatabase.login_oqyrman(email, password);
        if(res.getCount()==1){
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Qūttyqtaimyn!")
                    .setContentText("Sättı aiaqtaldy!")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            final Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                    })
                    .show();
        }else {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Habar")
                    .setContentText("Qūpia sözıñız qate")
                    .setConfirmText("OK")
                    .show();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }
}