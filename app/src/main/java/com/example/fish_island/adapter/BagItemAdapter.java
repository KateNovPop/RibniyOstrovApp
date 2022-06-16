package com.example.fish_island.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fish_island.R;
import com.example.fish_island.model.BagItem;
import com.example.fish_island.model.Product;

import java.util.List;

public class BagItemAdapter extends RecyclerView.Adapter<BagItemAdapter.BagItemViewHolder> {
    Context context;
    List<Product> bagItems;
    //вставка в адаптер макета
    public BagItemAdapter(Context context, List<Product> bagItems) {
        this.context = context;
        this.bagItems = bagItems;
    }
    //берем визуал
    @NonNull
    @Override
    public BagItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bagI = LayoutInflater.from(context).inflate(R.layout.bag_item,parent,false);
        return new BagItemAdapter.BagItemViewHolder(bagI);
    }
    //поиск и вставка картинки
    @Override
    public void onBindViewHolder(@NonNull BagItemViewHolder holder, int position) {
        int imageId = context.getResources().getIdentifier(bagItems.get(position).getImg(),"drawable",context.getPackageName());
        holder.bagImage.setImageResource(imageId);

        holder.bagName.setText(bagItems.get(position).getName());
        holder.bagPrice.setText(bagItems.get(position).getPrice());
    }
    //возвр кол эл ско созд
    @Override
    public int getItemCount() {
        return bagItems.size();
    }


    public class BagItemViewHolder extends RecyclerView.ViewHolder {

        ImageView bagImage;
        TextView bagName,bagPrice;
        public BagItemViewHolder(@NonNull View itemView) {
            super(itemView);

            bagImage = itemView.findViewById(R.id.imagebag);
            bagName = itemView.findViewById(R.id.NameBag);
            bagPrice = itemView.findViewById(R.id.PriceBag);
        }
    }
}
