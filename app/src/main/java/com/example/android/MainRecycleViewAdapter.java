package com.example.android;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.model.Feature;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {
    private ArrayList<Feature> mFeatures;

    private final MainOnclickHandler mClickHandler;

    public MainRecycleViewAdapter(ArrayList<Feature> features, MainOnclickHandler mClickHandler) {
        this.mFeatures = features;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public MainRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);

        // create a new view
        View activityListItem = inflator.inflate(R.layout.activities, parent, false);
        MainRecycleViewAdapter.ViewHolder viewHolder = new ViewHolder(activityListItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecycleViewAdapter.ViewHolder holder, int position) {
        // holder is the (already created) activity_list_item (= ViewHolder-object)
        // - gets element from your dataset at this position
        // - replaces the contents of the view with that element
        final Feature feature = mFeatures.get(position);

        holder.name.setText(feature.getObject());
        holder.geography.setText(feature.getGeography());
        holder.identification.setText(feature.getIdentification());
        Picasso.get().load(Uri.parse(feature.getImageUrl())).into(holder.image);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mFeatures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // ViewHolder is the view of a activity_list_view
        // one drink_list_item contains 3 views
        // Provide a reference to each view in the activity_list_item
        private TextView id;
        private TextView name;
        private TextView geography;
        private TextView identification;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            id              = itemView.findViewById(R.id.feature_id);
            name            = itemView.findViewById(R.id.feature_object);
            geography       = itemView.findViewById(R.id.feature_geography);
            identification  = itemView.findViewById(R.id.feature_identification);
            image           = itemView.findViewById(R.id.feature_image);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onActivityClick(v, this.getLayoutPosition(), mFeatures);
        }
    }
}
