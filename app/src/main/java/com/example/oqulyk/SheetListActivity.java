package com.example.oqulyk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SheetListActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ListView sheetList;
    private ArrayAdapter adapter;
    private ArrayList<String> listItems=new ArrayList();
    private long gid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_list);
        setContentView(R.layout.activity_sheet_list);

        setToolbar();

        gid=getIntent().getLongExtra("gid",-1);
        Log.i("1234567890","onCreate: "+gid);
        loadListItems();
        sheetList=findViewById(R.id.sheetList);
        adapter=new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(adapter);

        sheetList.setOnItemClickListener(((parent, view, position, id) -> openSheetActivity(position)));
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar2);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle=toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back=toolbar.findViewById(R.id.back);
        ImageButton save=toolbar.findViewById(R.id.save);

        title.setText("Nätije tızımı");
        subtitle.setVisibility(View.GONE);
        save.setVisibility(View.INVISIBLE);

        toolbar=findViewById(R.id.toolbar2);
        back.setOnClickListener(v -> onBackPressed());
    }

    private void openSheetActivity(int position) {
        long [] idArray=getIntent().getLongArrayExtra("idArray");
        int [] rollArray=getIntent().getIntArrayExtra("rollArray");
        String [] nameArray=getIntent().getStringArrayExtra("nameArray");


        Intent intent=new Intent(this,SheetActivity.class);
        intent.putExtra("idArray",idArray);
        intent.putExtra("rollArray",rollArray);
        intent.putExtra("nameArray",nameArray);
        intent.putExtra("month",listItems.get(position));
        startActivity(intent);
    }

    private void loadListItems() {
        Cursor cursor=new DBHelper(this).getDistinctMonth(gid);

        Log.i("1234567890","loadListItems: "+cursor.getCount());
        while (cursor.moveToNext()){
            @SuppressLint("Range") String date=cursor.getString(cursor.getColumnIndex(DBHelper.DATE_KEY));//01.04.2020
            listItems.add(date.substring(3));
        }
    }
    }
