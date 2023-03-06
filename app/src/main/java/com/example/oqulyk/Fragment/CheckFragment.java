package com.example.oqulyk.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.DBHelper;
import com.example.oqulyk.Model2.Genre;
import com.example.oqulyk.Model2.GenreAdapter;
import com.example.oqulyk.OqulykActivity;
import com.example.oqulyk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CheckFragment extends Fragment {
    Toolbar toolbar;
    TextView title, subtitle;
    ImageButton back, save;
    GenreAdapter genreAdapter;
    ArrayList<Genre> genres = new ArrayList<>();
    EditText est01, edt02;
    DBHelper dbHelper;
    String genreName, authorName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        dbHelper = new DBHelper(getActivity());
        loadData();

        genreAdapter = new GenreAdapter(getContext(), genres);
        recyclerView.setAdapter(genreAdapter);
        genreAdapter.setOnItemClickListener(position -> gotoItemActivity(position));
    }


    private void gotoItemActivity(int position) {
        Intent intent = new Intent(getContext(), OqulykActivity.class);
        intent.putExtra("genreName", genres.get(position).getGenre());
        intent.putExtra("authorName", genres.get(position).getAuthor());
        intent.putExtra("position", position);
        intent.putExtra("gid", genres.get(position).getGid());
        startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbar();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getGenreTable();
        genres.clear();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.GENRE_ID));
            @SuppressLint("Range") String genreName = cursor.getString(cursor.getColumnIndex(DBHelper.GENRE_NAME_KEY));
            @SuppressLint("Range") String authorName = cursor.getString(cursor.getColumnIndex(DBHelper.AUTHOR_NAME_KEY));

            genres.add(new Genre(id, genreName, authorName));
        }

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Oqulyğymdy engızu");

        est01 = view.findViewById(R.id.est01);
        edt02 = view.findViewById(R.id.edt02);

        est01.setHint("Kıtap janryn jazyñyz");
        edt02.setHint("Kıtap avtory");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);


        cancel.setOnClickListener(v -> dialog.dismiss());
        add.setOnClickListener(v -> {
            addGenre(genreName, authorName);
            dialog.dismiss();
        });
    }

    private void addGenre(String genreName, String authorName) {
        genreName = est01.getText().toString();
        authorName = edt02.getText().toString();
        long gid = dbHelper.addGenre(genreName, authorName);
        Genre genre = new Genre(gid, genreName, authorName);
        genres.add(genre);
        genreAdapter.notifyDataSetChanged();

    }

    private void setToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        title = getActivity().findViewById(R.id.title_toolbar);
        subtitle = getActivity().findViewById(R.id.subtitle_toolbar);
        back = getActivity().findViewById(R.id.back);
        save = getActivity().findViewById(R.id.save);

        title.setText("Deñgeiım");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1:
                deleteClass(item.getGroupId());
        }

        return super.onContextItemSelected(item);
    }

    private void deleteClass(int position) {
        dbHelper.deleteClass(genres.get(position).getGid());
        genres.remove(position);
        genreAdapter.notifyItemRemoved(position);
    }

    private void showUpdateDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Oqulyqty özgertu");

        est01 = view.findViewById(R.id.est01);
        edt02 = view.findViewById(R.id.edt02);

        est01.setHint("Kıtap janryn jazyñyz");
        edt02.setHint("Kıtap avtory");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);
        add.setText("BASU");

        cancel.setOnClickListener(v -> dialog.dismiss());
        add.setOnClickListener(v -> {
            genreName = est01.getText().toString();
            authorName = edt02.getText().toString();
            updateClassName(position, genreName, authorName);
            dialog.dismiss();
        });
    }

    private void updateClassName(int position, String genreName, String authorName) {
        genres.get(position).setGenre(genreName);
        genres.get(position).setAuthor(authorName);
        genreAdapter.notifyItemChanged(position);
        dbHelper.updateClass(genres.get(position).getGid(), genreName, authorName);
    }
}