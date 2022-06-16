package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fish_island.model.BagItem;
import com.example.fish_island.model.LikeItem;
import com.google.android.material.snackbar.Snackbar;

public class ProductActivity extends AppCompatActivity {

    ConstraintLayout Root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Root = findViewById(R.id.rootProduct);

        //Назад
        ImageButton backButton = (ImageButton) findViewById(R.id.BackProductButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //поиск в дизайне
        TextView ProductName = findViewById(R.id.ProductName);
        TextView ProductPrice = findViewById(R.id.ProductPrice);
        TextView ProductDescription = findViewById(R.id.ProductDescription);
        ImageView ProductImage = findViewById(R.id.ProductImage);

        //Присвоение значений
        ProductName.setText(getIntent().getStringExtra("ProductName"));
        ProductPrice.setText(getIntent().getStringExtra("ProductPrice"));
        ProductDescription.setText(getIntent().getStringExtra("ProductDescription"));
        ProductImage.setImageResource(getIntent().getIntExtra("ProductImg", 0));

    }

    //добавление в корзину
    public void addToBag(View view){
        int id = getIntent().getIntExtra("ProductId",0);
        BagItem.item_id.add(id);
        Snackbar.make(Root,"Добавлено в корзину",Snackbar.LENGTH_LONG).show();
    }

    public void addToLike(View view){
        int id = getIntent().getIntExtra("ProductId",0);
        LikeItem.item_id.add(id);
        Snackbar.make(Root,"Добавлено в избранное ",Snackbar.LENGTH_LONG).show();
    }
}