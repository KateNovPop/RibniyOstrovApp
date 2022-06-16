package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    String Total,Name,Adress,Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Name = getIntent().getStringExtra("Name");
        Adress = getIntent().getStringExtra("Adress");
        Phone = getIntent().getStringExtra("Phone");

        //Итого
        TextView totalView = (TextView) findViewById(R.id.total2);
        Total = getIntent().getStringExtra("total");
        totalView.setText(Total);

        //назад
        ImageButton backButton = (ImageButton) findViewById(R.id.back6Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        //переход на след активити
        ImageButton NextStep = (ImageButton) findViewById(R.id.FinishStepButton);
        NextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("fishIsland.intent.action.orderCreated");
                intent.putExtra("Name",Name);
                intent.putExtra("Adress",Adress);
                intent.putExtra("Phone",Phone);
                intent.putExtra("Total",Total);
                startActivity(intent);
            }
        });
    }
}