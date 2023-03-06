package com.example.oqulyk.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.oqulyk.DBHelper;
import com.example.oqulyk.R;
import com.example.oqulyk.ResetActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgetActivity extends AppCompatActivity {
    ImageView back_to_login;
    EditText nameEt;
    DBHelper dbHelper;
    Button rastauBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        dbHelper=new DBHelper(this);

        back_to_login=findViewById(R.id.backBtn);
        back_to_login.setOnClickListener(v ->  {
               startActivity(new Intent(ForgetActivity.this, LoginActivity.class));
        });

        nameEt=findViewById(R.id.nameEt);
        rastauBtn=findViewById(R.id.rastauBtn);

        rastauBtn.setOnClickListener(v ->  {
                String name=nameEt.getText().toString();
                Boolean checkUser=dbHelper.checkUsername(name);
                if(checkUser==true){
                    Intent intent=new Intent(ForgetActivity.this, ResetActivity.class);
                    intent.putExtra("NAME",name);
                    startActivity(intent);
                }

                else {
                    new SweetAlertDialog(ForgetActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Habarlama")
                            .setContentText("Būndai oqyrman tırkelmegen")
                            .setConfirmText("OK")
                            .show();
                }
        });
    }
}
