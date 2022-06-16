package com.example.fish_island.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fish_island.ProductActivity;
import com.example.fish_island.R;
import com.example.fish_island.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> products;
//вставка в адаптер макета
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
//берем визуал
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItems = LayoutInflater.from(context).inflate(R.layout.product_item, parent,false);
        return new ProductAdapter.ProductViewHolder(productItems);
    }
//поиск и вставка картинки
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        int imageId = context.getResources().getIdentifier(products.get(position).getImg(), "drawable", context.getPackageName());
        holder.productImage.setImageResource(imageId);

        holder.productName.setText(products.get(position).getName());
        holder.productDescription.setText(products.get(position).getProduct_description());
        holder.productPrice.setText(products.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class); //к с id и наз эл-та

                intent.putExtra("ProductName",products.get(position).getName());
                intent.putExtra("ProductImg", imageId);
                intent.putExtra("ProductDescription",products.get(position).getProduct_description());
                intent.putExtra("ProductPrice",products.get(position).getPrice());
                intent.putExtra("ProductId",products.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override //возвр кол эл ско созд
    public int getItemCount() {
        return products.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productName, productDescription, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
