package com.example.clothingappframework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, addressEditText, cityEditText;
    private Button confirmOrderBtn;

    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this,"Total Price = $" + totalAmount,Toast.LENGTH_SHORT).show()

        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText = (EditText) findViewById(R.id.shipment_name);
        phoneEditText = (EditText) findViewById(R.id.shipment_phone_number);
        addressEditText = (EditText) findViewById(R.id.shipment_Address);
        cityEditText = (EditText) findViewById(R.id.shipment_city);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Check();
            }
        });

    }

    private void Check()
    {
        if(TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your full name.",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your Phone number.",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your address.",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your city name.",Toast.LENGTH_SHORT);
        }
        else
        {
            confirmOrder()
        }
    }

    private void confirmOrder()
    {

    }
}