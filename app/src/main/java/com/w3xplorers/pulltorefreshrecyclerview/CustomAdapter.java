package com.w3xplorers.pulltorefreshrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avijit on 8/3/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    ArrayList<String> android_version_names;// = new ArrayList<String>();
    ArrayList<String> android_image_urls;//  = new ArrayList<String>();
    private Context context;


    public CustomAdapter(Context context,ArrayList<String> android_version_names,ArrayList<String> android_image_urls) {
        this.context = context;
        this.android_version_names = android_version_names;
        this.android_image_urls = android_image_urls;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        final View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(itemView); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        // set the data in items
        //position = holder.getAdapterPosition();
        holder.vText.setText(android_version_names.get(position));
        Picasso.with(context)
                .load(android_image_urls.get(position))
                .placeholder(R.drawable.placeholder)                             // optional
                .error(R.drawable.error)
                .resize(120, 120)
                .into(holder.vImageView);

        // implement setOnClickListener event on item view.
        final int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, android_version_names.get(finalPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return android_version_names.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        protected TextView vText;
        protected ImageView vImageView;

        private CustomViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            vText = (TextView) itemView.findViewById(R.id.text);
            vImageView = (ImageView) itemView.findViewById(R.id.img);
        }

    }





}