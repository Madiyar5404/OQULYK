package com.example.oqulyk.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oqulyk.Book.Kitap;
import com.example.oqulyk.Book.KitapAdapter;
import com.example.oqulyk.Model4.CategoryAdaptor;
import com.example.oqulyk.Model4.CategoryDomain;
import com.example.oqulyk.R;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {
     Toolbar toolbar;
     TextView title;
     View v;
     RecyclerView recyclerView2,recyclerView;
     List<Kitap> list;
     KitapAdapter kitapAdapter;
     List<CategoryDomain> category;
     CategoryAdaptor categoryAdaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView2=v.findViewById(R.id.recyclerView2);
        kitapAdapter=new KitapAdapter(list,getContext());
        recyclerView2.setAdapter(kitapAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView=v.findViewById(R.id.recyclerView);
        categoryAdaptor=new CategoryAdaptor(category,getContext());
        recyclerView.setAdapter(categoryAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list=new ArrayList<>();
        list.add(new Kitap("Kemel adam","kem","Avtor būl kıtabynda jan-jaqty jetılgen idealdy adamnyñ qasietterı men erekşelıkterın tüsındıre otyryp, oqyrmanyn “kemel adam” boluğa şaqyrady.",5979));
        list.add(new Kitap("Mahabbat qyzyq mol jyldar","mahab","Avtor būl kıtabynda jan-jaqty jetılgen idealdy adamnyñ qasietterı men erekşelıkterın tüsındıre otyryp, oqyrmanyn “kemel adam” boluğa şaqyrady. ",4569));
        list.add(new Kitap("Dumai kak matematik","dumai","Avtor būl kıtabynda jan-jaqty jetılgen idealdy adamnyñ qasietterı men erekşelıkterın tüsındıre otyryp, oqyrmanyn “kemel adam” boluğa şaqyrady. ",6189));
        list.add(new Kitap("Papa mojet","papa","Avtor būl kıtabynda jan-jaqty jetılgen idealdy adamnyñ qasietterı men erekşelıkterın tüsındıre otyryp, oqyrmanyn “kemel adam” boluğa şaqyrady.",4879));


        category=new ArrayList<>();
        category.add(new CategoryDomain("A.Kunanbaev","author1"));
        category.add(new CategoryDomain("Y.Altynsarin","author6"));
        category.add(new CategoryDomain("Ğ.Müsırepov","author4"));
        category.add(new CategoryDomain("M.Äuezov","author2"));
        category.add(new CategoryDomain("O.Süleimenov","author3"));

        super.onCreate(savedInstanceState);
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
        title.setText("Kıtaptar tızımı");
    }

}