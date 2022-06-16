package com.example.fish_island.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fish_island.R;
import com.example.fish_island.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    Context context;
    List<Story> stories;
    //вставка в адаптер макета
    public StoryAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
    }


    //берем визуал
    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View storyItems = LayoutInflater.from(context).inflate(R.layout.story_item, parent,false);
        return new StoryViewHolder(storyItems);
    }
    //поиск и вставка картинки
    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        holder.storyTitle.setText(stories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static final class StoryViewHolder extends RecyclerView.ViewHolder{

        TextView storyTitle;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);

            storyTitle = itemView.findViewById(R.id.storyTitle);

        }
    }
}
