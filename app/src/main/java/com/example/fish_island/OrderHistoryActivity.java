package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OrderHistoryActivity extends AppCompatActivity {

    ImageButton backButton,makeOrder;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        //назад
        backButton = (ImageButton) findViewById(R.id.back3Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //заказ
       makeOrder = (ImageButton) findViewById(R.id.makeOrderButton);
        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OrderHistoryActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}