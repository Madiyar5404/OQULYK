package com.example.oqulyk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.oqulyk.Fragment.BasketFragment;
import com.example.oqulyk.Fragment.BookFragment;
import com.example.oqulyk.Fragment.CheckFragment;
import com.example.oqulyk.Fragment.HomeFragment;
import com.example.oqulyk.Fragment.NewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment=new HomeFragment();
    NewsFragment newsFragment=new NewsFragment();
    BasketFragment basketFragment=new BasketFragment();
    CheckFragment checkFragment=new CheckFragment();
    BookFragment bookFragment=new BookFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottom_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(item ->  {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,homeFragment).commit();
                        return true;
                    case R.id.news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,newsFragment).commit();
                        return true;
                    case R.id.basket:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,basketFragment).commit();
                        return true;
                    case R.id.book:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,bookFragment).commit();
                        return true;
                    case R.id.check:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,checkFragment).commit();
                        return true;
                }
                return false;
        });
    }

}