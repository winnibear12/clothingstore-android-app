package com.example.clothingappframework.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.clothingappframework.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.onClickListener
{
     public TextView txtProductName, txtProductPrice, txtProductQuantity;
     private ItemClickListener itemClickListener;

     public CartViewHolder(View itemView)
     {
         super(itemView);

         txtProductName = itemView.findViewById(R.id.cart_product_name);
         txtProductPrice = itemView.findViewById(R.id.cart_product_price);
         txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);


     }

     @override
     public void onClick(View view)
     {
        itemClickListener.onClick(view, getAdapterPosition(),false);
     }

     public void setItemClickListener(ItemClickListener itemClickListener)
     {
         this.itemClickListener = itemClickListener;
     }

}
