package com.example.fauziw97.taxapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fauziw97.taxapp.Model.SpeciesModel;
import com.example.fauziw97.taxapp.R;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Fauziw97 on 9/15/17.
 */
public class SpeciesAdapter extends FirebaseRecyclerAdapter<SpeciesAdapter.ViewHolder, SpeciesModel> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView speciesImage;
        TextView speciesName;
        TextView speciesLatin;

        public ViewHolder(View view) {
            super(view);
            speciesImage = (ImageView) view.findViewById(R.id.species_image);
            speciesName = (TextView) view.findViewById(R.id.species_name);
            speciesLatin = (TextView) view.findViewById(R.id.species_latin);
        }
    }

    public SpeciesAdapter(Query query, @Nullable ArrayList<SpeciesModel> items,
                     @Nullable ArrayList<String> keys) {
        super(query, items, keys);
    }

    @Override public SpeciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_species, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(SpeciesAdapter.ViewHolder holder, int position) {
        SpeciesModel species = getItem(position);
        holder.speciesImage.setImageResource(species.getSpeciesImage());
        holder.speciesName.setText(species.getSpeciesName());
        holder.speciesLatin.setText(species.getSpeciesLatin());
    }

    @Override protected void itemAdded(SpeciesModel item, String key, int position) {
        Log.d("SpeciesAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(SpeciesModel oldItem, SpeciesModel newItem, String key, int position) {
        Log.d("SpeciesAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(SpeciesModel item, String key, int position) {
        Log.d("SpeciesAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(SpeciesModel item, String key, int oldPosition, int newPosition) {
        Log.d("SpeciesAdapter", "Moved an item.");
    }
}
