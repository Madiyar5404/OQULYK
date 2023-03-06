package com.example.oqulyk.Book;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Kitap item){
        ArrayList<Kitap> listKitap=getListCart();
        boolean existAlready=false;
        int n=0;
        for (int i=0;i<listKitap.size();i++){
            if (listKitap.get(i).getTitle().equals(item.getTitle())){
               existAlready=true;
               n=i;
               break;
            }
        }

        if (existAlready){
            listKitap.get(n).setNumberInCart(item.getNumberInCart());
        }

        else {
            listKitap.add(item);
        }

        tinyDB.putListObject("CartList",listKitap);
        Toast.makeText(context,"Added to Your Cart",Toast.LENGTH_SHORT).show();
    }


    public ArrayList<Kitap> getListCart(){
        return tinyDB.getListObject("CartList");
    }



    public void plusNumberFood(ArrayList<Kitap> listKitap,int position, ChangeNumberItemsListener changeNumberItemsListener){
        listKitap.get(position).setNumberInCart(listKitap.get(position).getNumberInCart()+1);
        tinyDB.putListObject("Cartlist",listKitap);
        changeNumberItemsListener.changed();
    }


    public void minusNumberFood(ArrayList<Kitap> listKitap,int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listKitap.get(position).getNumberInCart()==1){
            listKitap.remove(position);
        }

        else{
            listKitap.get(position).setNumberInCart(listKitap.get(position).getNumberInCart()-1);
        }

        tinyDB.putListObject("CartList",listKitap);
        changeNumberItemsListener.changed();
    }


    public Double getTotalFee(){
        ArrayList<Kitap> listKitap=getListCart();
        double fee=0;
        for (int i=0;i<listKitap.size();i++){
            fee=fee+(listKitap.get(i).getFee()*listKitap.get(i).getNumberInCart());
        }

        return fee;
    }

}
