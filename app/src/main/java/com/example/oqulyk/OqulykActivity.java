package com.example.oqulyk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.Model3.Oqulyk;
import com.example.oqulyk.Model3.OqulykAdapter;

import java.util.ArrayList;


public class OqulykActivity extends AppCompatActivity {
    Toolbar toolbar;
    private String genreName;
    private String authorName;
    private int position;
    private RecyclerView recyclerView;
    private OqulykAdapter oqulykAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Oqulyk> oqulyktar =new ArrayList<>();
    private DBHelper dbHelper;
    private long gid;
    private MyCalendar calendar;
    private TextView subtitle,txtData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oqulyk);



        calendar=new MyCalendar();
        dbHelper=new DBHelper(this);
        Intent intent=getIntent();
        genreName=intent.getStringExtra("genreName");
        authorName=intent.getStringExtra("authorName");
        position=intent.getIntExtra("position",-1);
        gid=intent.getLongExtra("gid",-1);


        setToolbar();
        loadDataa();
        recyclerView=findViewById(R.id.oqulyk_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        oqulykAdapter=new OqulykAdapter(this, oqulyktar);
        recyclerView.setAdapter(oqulykAdapter);


        oqulykAdapter.setOnItemClickListener(position -> changeStatus(position));
        loadStatusData();
        txtData=findViewById(R.id.txtData);
        txtData.setText(calendar.getDate());
    }

    private void loadDataa() {
        Cursor cursor=dbHelper.getKitapTable(gid);
        Log.i("1234567890","loadData:"+gid);
        oqulyktar.clear();
        while (cursor.moveToNext()){
            @SuppressLint("Range") long kid=cursor.getLong(cursor.getColumnIndex(DBHelper.KITAP_ID));
            @SuppressLint("Range") int sany=cursor.getInt(cursor.getColumnIndex(DBHelper.KITAP_SANY_KEY));
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(DBHelper.KITAP_NAME_KEY));
            oqulyktar.add(new Oqulyk(kid,sany,name));
        }
        cursor.close();
    }

    private void changeStatus(int position) {
        String status=oqulyktar.get(position).getStatus();
        if (status.equals("OQYLDY")) status="OQYLMADY";
        else status="OQYLDY";

        oqulyktar.get(position).setStatus(status);
        oqulykAdapter.notifyItemChanged(position);
    }

    private void setToolbar(){

        toolbar=findViewById(R.id.toolbar2);
        TextView title=toolbar.findViewById(R.id.title_toolbar);
        subtitle=toolbar.findViewById(R.id.subtitle_toolbar);

        ImageButton back=toolbar.findViewById(R.id.back);
        ImageButton save=toolbar.findViewById(R.id.save);
        save.setOnClickListener(v -> saveStatus());

        title.setText(genreName);
        subtitle.setText(authorName);

        back.setOnClickListener(v -> onBackPressed());

        toolbar.inflateMenu(R.menu.oqulyk_menu);
        toolbar.setOnMenuItemClickListener(menuItem->onMenuItemClick(menuItem));

    }

    private void saveStatus() {
        for (Oqulyk oqulyk:oqulyktar){
            String status=oqulyk.getStatus();
            if (status!="OQYLDY") status="OQYLMADY";
            long value=dbHelper.addStatus(oqulyk.getKid(),gid,calendar.getDate(),status);

            if (value ==-1)
                dbHelper.updateStatus(oqulyk.getKid(),calendar.getDate(),status);
        }
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId()==R.id.add_oqulyk){
            showAddOqulykDialog();
        }
        else if(menuItem.getItemId()==R.id.show_Calendar){
            showCalendar();
        }
        else if(menuItem.getItemId()==R.id.show_attendance){
          openSheetList();
        }
        return true;
    }

    private void openSheetList() {
        long[] idArray=new long[oqulyktar.size()];
        int [] rollArray =new int[oqulyktar.size()];
        String[] nameArray=new String[oqulyktar.size()];

        for (int i=0;i< idArray.length;i++)
            idArray[i]=oqulyktar.get(i).getKid();
        for (int i=0;i< rollArray.length;i++)
            rollArray[i]=oqulyktar.get(i).getRoll();
        for (int i=0;i< nameArray.length;i++)
            nameArray[i]=oqulyktar.get(i).getName();

        Intent intent=new Intent(this,SheetListActivity.class);
        intent.putExtra("gid",gid);
        intent.putExtra("idArray",idArray);
        intent.putExtra("rollArray",rollArray);
        intent.putExtra("nameArray",nameArray);
        startActivity(intent);

    }

    private void showCalendar() {
        MyCalendar calendar=new MyCalendar();
        calendar.show(getSupportFragmentManager(),"");
        calendar.setOnCalendarOnClickListener(this :: onCalendarOkClicked);
    }

    private void onCalendarOkClicked(int year, int month, int day) {
        calendar.setDate(year,month,day);
        subtitle.setText(authorName);
        txtData.setText(calendar.getDate());
        loadStatusData();
    }

    private void loadStatusData(){
        for (Oqulyk oqulyk:oqulyktar) {
            String status = dbHelper.getStatus(oqulyk.getKid(),calendar.getDate());
            if (status != null) oqulyk.setStatus(status);
            else oqulyk.setStatus("");
        }

        oqulykAdapter.notifyDataSetChanged();
    }
    private void showAddOqulykDialog() {
       MyDialog dialog=new MyDialog();
       dialog.show(getSupportFragmentManager(),MyDialog.OQULYK_ADD_DIALOG);
       dialog.setListener((sany,name)->addOqulyk(sany,name));
    }

    private void addOqulyk(String sany_string, String name) {
        int sany=Integer.parseInt(sany_string);
        long kitapid=dbHelper.addKitap(gid,sany,name);
        Oqulyk oqulyk=new Oqulyk(kitapid,sany,name);
        oqulyktar.add(oqulyk);
        oqulykAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showUpdateOqulykDialog(item.getGroupId());
                break;
            case 1:
                deleteOqulyk(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateOqulykDialog(int position) {
        MyDialog dialog=new MyDialog(oqulyktar.get(position).getRoll(),oqulyktar.get(position).getName());
        dialog.show(getSupportFragmentManager(),MyDialog.OQULYK_UPDATE_DIALOG);
        dialog.setListener((sany_string,name)-> updateOqulyk(position,name));
    }

    private void updateOqulyk(int position, String name) {
        dbHelper.updateKitap(oqulyktar.get(position).getKid(),name);
        oqulyktar.get(position).setName(name);
        oqulykAdapter.notifyItemChanged(position);
    }

    private void deleteOqulyk(int position) {
        dbHelper.deleteKitap(oqulyktar.get(position).getKid());
        oqulyktar.remove(position);
        oqulykAdapter.notifyItemRemoved(position);
    }
}