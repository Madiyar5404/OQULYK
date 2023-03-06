package com.example.oqulyk.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.oqulyk.R;

public class NewsFragment extends Fragment {
    Toolbar toolbar;
    TextView title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        setToolbar();
        super.onResume();
    }

    private void setToolbar() {
        toolbar=getActivity().findViewById(R.id.toolbar);
        title=getActivity().findViewById(R.id.title_toolbar);
        title.setText("Jañalyqtar");
    }
}