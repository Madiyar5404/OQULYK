package com.example.oqulyk.Model4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    List<CategoryDomain> categoryDomains;
    Context context;

    public CategoryAdaptor(List<CategoryDomain> categoryDomains, Context context) {
        this.categoryDomains = categoryDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="author1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 1:{
                picUrl="author6";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 2:{
                picUrl="author4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 3:{
                picUrl="author2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 4:{
                picUrl="author3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
        }


        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        holder.categoryPic.setImageResource(drawableResourceId);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.categoryName);
            categoryPic=itemView.findViewById(R.id.categoryPic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
