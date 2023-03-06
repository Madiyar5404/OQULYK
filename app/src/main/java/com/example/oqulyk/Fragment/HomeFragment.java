package com.example.oqulyk.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oqulyk.ImageSliderAdapter;
import com.example.oqulyk.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 mViewPager;
    private List<Integer> mImageList;
    private ImageSliderAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = view.findViewById(R.id.view_pager);
        mImageList = new ArrayList<>();
        mImageList.add(R.drawable.slider1);
        mImageList.add(R.drawable.slider2);
        mImageList.add(R.drawable.slider3);
        mImageList.add(R.drawable.slider4);
        mImageList.add(R.drawable.slider5);
        mImageList.add(R.drawable.slider6);

        mAdapter = new ImageSliderAdapter(mImageList);
        mViewPager.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onResume() {
        super.onResume();

    }
}

