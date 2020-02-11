package com.example.android;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.model.Feature;

import java.util.List;


public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {
    private List<Feature> mActivities;

    private final MainOnclickHandler mClickHandler;

    public MainRecycleViewAdapter(List<Feature> activities, MainOnclickHandler mClickHandler) {
        this.mActivities = activities;
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
        final Feature feature = mActivities.get(position);

        holder.name.setText(feature.getObject());
        holder.description.setText(feature.getDescription());



    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mActivities.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // ViewHolder is the view of a activity_list_view
        // one drink_list_item contains 3 views
        // Provide a reference to each view in the activity_list_item
        private TextView name;
        private TextView description;

        private TextView id;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.activity_item_name);
            description = (TextView) itemView.findViewById(R.id.activity_item_description);
            id = (TextView) itemView.findViewById(R.id.activity_item_id);

        }

        @Override
        public void onClick(View v) {
            mClickHandler.onActivityClick(v, Integer.parseInt(id.getText().toString()));
        }
    }
}
