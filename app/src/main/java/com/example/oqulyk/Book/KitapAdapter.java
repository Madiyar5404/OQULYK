package com.example.oqulyk.Book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.R;

import java.util.List;

public class KitapAdapter extends RecyclerView.Adapter<KitapAdapter.ViewHolder> {
    List<Kitap> list;
    Context context;

    public KitapAdapter(List<Kitap> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.viewholder_kitaptar,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.fee.setText(String.valueOf(list.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(list.get(position).getImage(),"drawable",holder.itemView.getContext().getPackageName());
        holder.pic.setImageResource(drawableResourceId);

        holder.addBtn.setOnClickListener(v -> {
                            Intent intent=new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", list.get(position));
                holder.itemView.getContext().startActivity(intent);
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        Button addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);
        }
    }
    }

