package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EmptyBagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_bag);

        //кнока назад
        ImageButton backButton = (ImageButton) findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //сделать заказ
        ImageButton makeOrder = (ImageButton) findViewById(R.id.makeOrder2Button);
        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmptyBagActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}