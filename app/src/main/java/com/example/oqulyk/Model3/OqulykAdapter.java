package com.example.oqulyk.Model3;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.R;

import java.util.ArrayList;


public class OqulykAdapter extends RecyclerView.Adapter<OqulykAdapter.OqulykViewHolder>{
    ArrayList<Oqulyk> oqulyks;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface  OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OqulykAdapter(Context context, ArrayList<Oqulyk> oqulyks) {
        this.oqulyks = oqulyks;
        this.context = context;
    }

    public static class OqulykViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView roll,name,status;
        CardView cardView;

        public OqulykViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            roll=itemView.findViewById(R.id.roll);
            name=itemView.findViewById(R.id.name);
            status=itemView.findViewById(R.id.status);
            cardView=itemView.findViewById(R.id.cardview);

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
    public OqulykViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_oqulyks,parent,false);;
        return new OqulykViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OqulykViewHolder holder, int position) {
        holder.roll.setText(oqulyks.get(position).getRoll()+"");
        holder.name.setText(oqulyks.get(position).getName());
        holder.status.setText(oqulyks.get(position).getStatus());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }

    private int getColor(int position) {
        String status=oqulyks.get(position).getStatus();
        if (status.equals("OQYLDY"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.OK)));
        else if(status.equals("OQYLMADY"))
            return  Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.NO)));
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, R.color.normal)));
    }

    @Override
    public int getItemCount() {
        return oqulyks.size();
    }

}
