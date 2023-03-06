package com.example.oqulyk.Book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oqulyk.R;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private Kitap object;
    int numberOrder=1;
    private ManagementCart managementCart;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);


        managementCart=new ManagementCart(this);

        setToolbar();

        initView();
        getBundle();

    }

    private void setToolbar() {

        toolbar=findViewById(R.id.toolbar2);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back=toolbar.findViewById(R.id.back);
        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText("Kıtap turaly");
        subtitle.setVisibility(View.GONE);
        save.setVisibility(View.INVISIBLE);

        back.setOnClickListener(v -> onBackPressed());
    }

    private void getBundle() {
        object= (Kitap) getIntent().getSerializableExtra("object");

        int drawableResourseId=this.getResources().getIdentifier(object.getImage(),"drawable",this.getPackageName());
        picFood.setImageResource(drawableResourseId);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getFee()+" ₸");
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });

    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn=findViewById(R.id.MinusBtn);
        picFood=findViewById(R.id.picfood);

    }


}