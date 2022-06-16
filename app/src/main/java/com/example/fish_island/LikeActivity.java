package com.example.fish_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fish_island.adapter.BagItemAdapter;
import com.example.fish_island.model.LikeItem;
import com.example.fish_island.model.Product;

import java.util.ArrayList;
import java.util.List;

public class LikeActivity extends AppCompatActivity {

    RecyclerView likeRecycler;
    BagItemAdapter likeAdapter;

    List<Product> like_item = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        like_item.clear();

        for (Product p : MainActivity.fullProductList){
            if(LikeItem.item_id.contains(p.getId())){like_item.add(p);}
        }
        setLikeItemRecycler(like_item);

        ImageButton backButton = (ImageButton) findViewById(R.id.back7Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setLikeItemRecycler(List<Product> like_items) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        likeRecycler = findViewById(R.id.likeRecycler);
        likeRecycler.setLayoutManager(layoutManager);

        likeAdapter = new BagItemAdapter(this, like_items);
        likeRecycler.setAdapter(likeAdapter);

        //удаление
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                int position = target.getAdapterPosition();
                like_items.remove(position);
                likeAdapter.notifyDataSetChanged();
                if(like_items.isEmpty()){
                    LikeItem.item_id.clear();}
            }
        });
        helper.attachToRecyclerView(likeRecycler);
    }
}