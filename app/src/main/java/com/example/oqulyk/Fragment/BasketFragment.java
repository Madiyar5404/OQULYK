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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.oqulyk.Book.CartListAdapter;
import com.example.oqulyk.Book.ChangeNumberItemsListener;
import com.example.oqulyk.Book.ManagementCart;
import com.example.oqulyk.R;

public class BasketFragment extends Fragment {
     Toolbar toolbar;
     TextView title;
     RecyclerView.Adapter adapter;
     RecyclerView recyclerView;
     ManagementCart managementCart;
     TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
     double tax;
     ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_basket, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        managementCart=new ManagementCart(getContext());

        totalFeeTxt=view.findViewById(R.id.totalFeeTxt);
        taxTxt=view.findViewById(R.id.taxTxt);
        deliveryTxt=view.findViewById(R.id.deliveryTxt);
        totalTxt=view.findViewById(R.id.totalTxt);
        emptyTxt=view.findViewById(R.id.emptyTxt);
        scrollView=view.findViewById(R.id.scrollView2);
        totalTxt=view.findViewById(R.id.totalTxt);
        recyclerView=view.findViewById(R.id.cartView);

        RecyclerView recyclerView = getView().findViewById(R.id.cartView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), getContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerView.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        setToolbar();
        CalculateCart();
        super.onResume();
    }

    public void CalculateCart() {

        double percentTax=0.02;
        double delivery=10;

        tax=Math.round((managementCart.getTotalFee()*percentTax)*100)/100;

        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round((managementCart.getTotalFee()*100)/100);

        totalFeeTxt.setText(itemTotal+" ₸");
        taxTxt.setText(tax+" ₸");
        deliveryTxt.setText(delivery+" ₸");
        totalTxt.setText(total+" ₸");

    }

    private void setToolbar() {
        toolbar=getActivity().findViewById(R.id.toolbar);
        title=getActivity().findViewById(R.id.title_toolbar);
        title.setText("Sebet");
    }

}