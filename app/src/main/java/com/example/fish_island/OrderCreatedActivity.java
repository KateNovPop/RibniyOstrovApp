package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fish_island.model.BagItem;
import com.example.fish_island.model.Order;
import com.example.fish_island.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class OrderCreatedActivity extends AppCompatActivity {
    Intent intent;

    FirebaseDatabase db;
    DatabaseReference order;

    String Name,Adress,Phone;

    List<Product> Order_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_created);

        db= FirebaseDatabase.getInstance();
        order = db.getReference("Orders");

        Name = getIntent().getStringExtra("Name");
        Adress = getIntent().getStringExtra("Adress");
        Phone = getIntent().getStringExtra("Phone");

        Order_items.clear();

        Order_items.addAll(BagActivity.bagItemList);


        Order orders = new Order();
        orders.setName(Name);
        orders.setAdress(Adress);
        orders.setPhone(Phone);
        orders.setOrder_items(Order_items);

        order.push().setValue(orders).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
                //intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"katenov21@gmail.com" ));
            }
       });

        BagItem.item_id.clear();

        //назад на начальную страницу
        ImageButton back = (ImageButton) findViewById(R.id.backToMainButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OrderCreatedActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        
    }
}