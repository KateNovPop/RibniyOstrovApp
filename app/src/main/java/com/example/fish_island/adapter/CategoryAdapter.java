package com.example.fish_island.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fish_island.MainActivity;
import com.example.fish_island.R;
import com.example.fish_island.model.Category;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categories;
    //вставка в адаптер макета
    public CategoryAdapter(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
    }
    //берем визуал
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent,false);
        return new CategoryViewHolder(categoryItems);
    }
    //поиск и вставка картинки
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.categoryTitle.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showProductByCategory(categories.get(position).getId()); //к с id и наз эл-та
            }
        });
    }
    //возвр кол эл ско созд
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);

        }
    }

}
