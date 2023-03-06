package com.example.oqulyk.Model2;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.R;

import java.util.ArrayList;


public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{
    ArrayList<Genre> list;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface  OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public GenreAdapter(Context context, ArrayList<Genre> genres) {
        this.list = genres;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView genre,author;
        public ViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            genre=itemView.findViewById(R.id.genre);
            author=itemView.findViewById(R.id.author);
            itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),0,0,"ÖZGERTU");
            menu.add(getAdapterPosition(),1,0,"ÖŞIRU");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_genres,parent,false);;
        return new ViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.genre.setText(list.get(position).getGenre());
        holder.author.setText(list.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
