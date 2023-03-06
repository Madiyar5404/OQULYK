package com.example.oqulyk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oqulyk.Authorization.ForgetActivity;
import com.example.oqulyk.Authorization.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResetActivity extends AppCompatActivity {
    EditText nameEt,repasswordEt,recPasswordEt;
    ImageView back_to_login;
    Button rastauBtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        nameEt=findViewById(R.id.nameEt);
        repasswordEt=findViewById(R.id.repasswordEt);
        recPasswordEt=findViewById(R.id.recPasswordEt);
        rastauBtn=findViewById(R.id.rastauBtn);
        dbHelper=new DBHelper(this);

        back_to_login=findViewById(R.id.backBtn);
        back_to_login.setOnClickListener(v ->  {
            startActivity(new Intent(ResetActivity.this, ForgetActivity.class));
        });

        Intent intent=getIntent();
        nameEt.setText(intent.getStringExtra("NAME"));

        rastauBtn.setOnClickListener(v ->  {
                String name=nameEt.getText().toString();
                String password=repasswordEt.getText().toString();
                String repass=recPasswordEt.getText().toString();
               dbHelper.updatePassword(name,password);
                new SweetAlertDialog(ResetActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Qūttyqtaimyn!")
                        .setContentText("Sättı aiaqtaldy!")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                startActivity(new Intent(ResetActivity.this, LoginActivity.class));
                            }
                        })
                        .show();
        });
    }
}