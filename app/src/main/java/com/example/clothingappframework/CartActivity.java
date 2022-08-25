package com.example.clothingappframework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothingappframework.Model.Cart;
import com.example.clothingappframework.ui.CartViewHolder;

public class CartActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button next_process_button;
    private TextView txtTotalAmount;

    private int overTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        next_process_button = (Button) findViewById(R.id.next_process_button);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);

        NextProcessBtn.setOnClickListener(new View.onClickListener()){
            @Override
                    public void onClick(View view)
        {
            txtTotalAmount.setText("Total Price = $" + String.valueOf(overTotalPrice));

            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrderActivity.class);
            intent.putExtra("Total Price",String.valueOf(overTotalPrice));
            startActivity(intent);
            finish();
        }
    }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // this is for database
        final DatabaseReference cartListRef  = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecylerOptions<Cart> options = new FireBaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products"),Cart.class.build());

        FirebaseRecylerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(){
            @Override
            protected void onBindViewHolder(@nonNUll CartViewHolder holder, int position, @NonNull Cart model){

                holder.txtProductQuantity.setText(model.getQuantity());
                holder.txtProductPrice.setText(model.getPrice());
                holder.txtProductQuantity.setText(model.getpName());

                // quantity * price of just one product
                int oneTypeProductTPrice = ((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
                overTotalPrice = overTotalPrice + oneTypeProductTPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which)
                            {
                                if(i == 0)
                                {
                                    //productDetailsActivity need to implement
                                    Intent intent = new Intent(CartActivity.this,ProductDetailsActivity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);
                                }
                                if(i == 1) //delete
                                {
                                    cartListRef.child("User View")
                                            .child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new MediaPlayer.OnCompletionListener<Void>(){
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(CartActivity.this, "Item removed successfully.", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show()
                    }
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull viewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }

        }
    }

}