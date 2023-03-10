package com.example.oqulyk.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.oqulyk.DBHelper;
import com.example.oqulyk.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {
    ImageView back_to_login;
    EditText nameEt,emailEt,passwordEt,cPasswordEt;
    Button registerBtn;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myDatabase=new DBHelper(this);

        nameEt=findViewById(R.id.nameEt);
        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        cPasswordEt=findViewById(R.id.cPasswordEt);
        registerBtn=findViewById(R.id.registerBtn);

        Register_oqyrman();

        back_to_login=findViewById(R.id.backBtn);
        back_to_login.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            });


        TextInputLayout passwordTil=findViewById(R.id.passwordTil);
        EditText passwordEt=findViewById(R.id.passwordEt);

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password=s.toString();
                if (password.length() >= 8){
                    Pattern pattern= Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher=pattern.matcher(password);
                    boolean isPwdContainsSpeChar= matcher.find();
                    if (isPwdContainsSpeChar){
                        passwordTil.setHelperText("Keremet q??pias??z");
                        passwordTil.setError("");
                    }
                    else{
                        passwordTil.setHelperText("");
                        passwordTil.setError("??ls??z q??pias??z,_/*-@ degen siaqty ta??ba qosy??yz");
                    }
                }
                else{
                    passwordTil.setHelperText("E?? az 8 ta??ba eng??z??????z");
                    passwordTil.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void Register_oqyrman() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEt.getText().toString();
                String email=emailEt.getText().toString();
                String password=passwordEt.getText().toString();
                String cPassword=cPasswordEt.getText().toString();

                if(!password.equals(cPassword)) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habar")
                            .setContentText("Q??pia s??z??????z s??ikes kelmeid??!")
                            .setConfirmText("OK")
                            .show();
                }
                else if(!isEmailValid(email)) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habar")
                            .setContentText("@ qoiudy ??mytyp kett??????z")
                            .setConfirmText("OK")
                            .show();
                }
                else if(!isPasswordValid(password)) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habar")
                            .setContentText("Q??pia s??z??????z 8 den k??p boluy kerek")
                            .setConfirmText("OK")
                            .show();
                }
                else if(email.isEmpty()) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habar")
                            .setContentText("Pochta b??l??m??n toltyry??yz")
                            .setConfirmText("OK")
                            .show();
                }
                else if(password.isEmpty()) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habar")
                            .setContentText("Q??pia s??z b??l??m??n toltyry??yz")
                            .setConfirmText("OK")
                            .show();
                }
                else{
                    boolean isInserted = myDatabase.add_oqyrman(name, email, password);

                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Q??ttyqtaimyn!")
                            .setContentText("S??tt?? aiaqtaldy!")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                }
                            })
                            .show();
                }
            }
        });

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() > 7;
    }
}