package com.example.oqulyk.Book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oqulyk.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<Kitap> kitapDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<Kitap> kitapDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.kitapDomains = kitapDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
         return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     holder.title.setText(kitapDomains.get(position).getTitle());
     holder.feeEachItem.setText(String.valueOf(kitapDomains.get(position).getFee()));
     holder.totalEachItem.setText(String.valueOf(Math.round((kitapDomains.get(position).getNumberInCart()* kitapDomains.get(position).getFee())*100)/100));
     holder.num.setText(String.valueOf(kitapDomains.get(position).getNumberInCart()));

     int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(kitapDomains.get(position).getImage(),"drawable",holder.itemView.getContext().getPackageName());
     holder.pic.setImageResource(drawableResourceId);

     holder.plusItem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             managementCart.plusNumberFood(kitapDomains, position, new ChangeNumberItemsListener() {
                 @Override
                 public void changed() {
                     notifyDataSetChanged();
                     changeNumberItemsListener.changed();
                 }
             });
         }
     });

     holder.minusItem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             managementCart.minusNumberFood(kitapDomains, position, new ChangeNumberItemsListener() {
                 @Override
                 public void changed() {
                     notifyDataSetChanged();
                     changeNumberItemsListener.changed();
                 }
             });
         }
     });


    }

    @Override
    public int getItemCount() {
        return kitapDomains.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
