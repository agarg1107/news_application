package com.example.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class newsadapter extends RecyclerView.Adapter<newsadapter.viewholder>{
    ArrayList<newsmodel> list;
    Context context;

    public newsadapter(ArrayList<newsmodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newslayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        newsmodel newsmodel = list.get(position);
        holder.description.setText(newsmodel.getDescription());
        Glide.with(holder.itemView).load(newsmodel.getThumbnail()).into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String url = newsmodel.getUrl();
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();


                    customTabsIntent.launchUrl(MainActivity.activity, Uri.parse(url));

                }
                catch (Exception e)
                {
                    Log.d("click",e+"");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView description;
        ImageView thumbnail;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.decsnews);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
