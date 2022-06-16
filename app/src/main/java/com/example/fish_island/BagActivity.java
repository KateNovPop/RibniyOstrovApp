package com.example.fish_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fish_island.adapter.BagItemAdapter;
import com.example.fish_island.adapter.CategoryAdapter;
import com.example.fish_island.model.AuthToken;
import com.example.fish_island.model.BagItem;
import com.example.fish_island.model.Category;
import com.example.fish_island.model.Product;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BagActivity extends AppCompatActivity {

    RecyclerView bagRecycler;
    BagItemAdapter bagItemAdapter;
    ConstraintLayout Root;

    static  List<Product> bagItemList = new ArrayList<>();

    int sum = 0;
    String Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        bagItemList.clear();


        for (Product p : MainActivity.fullProductList){
            if(BagItem.item_id.contains(p.getId())){bagItemList.add(p);}
        }
        setBagItemRecycler(bagItemList);

        //назад
        ImageButton backButton = (ImageButton) findViewById(R.id.back4Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Доставка
        Root = (ConstraintLayout) findViewById(R.id.RootBag);
        ImageButton NextStep = (ImageButton) findViewById(R.id.NextStepDelivery);

        NextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AuthToken.Token.isEmpty()){
                    Snackbar.make(Root,"Пожалуйста зарегистрируйтесь или войдете",Snackbar.LENGTH_LONG).show();
                }
                else {
                    for (int i = 0; i < bagItemList.size(); i++) {
                        sum = sum + Integer.parseInt(bagItemList.get(i).getPrice());
                    }
                    Total = String.valueOf(sum);
                    Intent intent = new Intent("fishIsland.intent.action.delivery");
                    intent.putExtra("total", Total);
                    startActivity(intent);
                }
            }
        });
    }


    //блок с товарами
    private void setBagItemRecycler(List<Product> bagItemList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        bagRecycler = findViewById(R.id.bagRecucler);
        bagRecycler.setLayoutManager(layoutManager);

        bagItemAdapter = new BagItemAdapter(this, bagItemList);
        bagRecycler.setAdapter(bagItemAdapter);

        //удаление
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                int position = target.getAdapterPosition();
                bagItemList.remove(position);
                bagItemAdapter.notifyDataSetChanged();
                sum = 0;
                if(bagItemList.isEmpty()){BagItem.item_id.clear();}
            }
        });
        helper.attachToRecyclerView(bagRecycler);
    }
}