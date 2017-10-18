package com.example.fauziw97.taxapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fauziw97.taxapp.Model.SpeciesModel;
import com.example.fauziw97.taxapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fauziw97 on 9/15/17.
 */
public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.ViewHolder> {

    private FirebaseAuth firebaseAuth;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    private List<SpeciesModel> speciesModels;
    private Context context;

    public SpeciesAdapter(List<SpeciesModel> speciesModels, Context context) {
        this.speciesModels = speciesModels;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.species_image) ImageView speciesImage;
        @BindView(R.id.species_name) TextView speciesName;
        @BindView(R.id.species_status) TextView speciesStatus;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

   /*public SpeciesAdapter(Query query, @Nullable ArrayList<SpeciesModel> items,
                     @Nullable ArrayList<String> keys) {
        super(query, items, keys);
    }*/

    @Override public SpeciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_species, parent, false);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(SpeciesAdapter.ViewHolder holder, int position) {
        SpeciesModel species = speciesModels.get(position);
        /*Picasso.with(context)
                .load(species.getSpeciesImage())
                .into(holder.speciesImage);*/
        holder.speciesName.setText(species.getSpeciesName());
        holder.speciesStatus.setText(species.getSpeciesStatus());
    }

    @Override
    public int getItemCount() {
        return speciesModels.size();
    }

    //Need to add onclick here



   /* @Override protected void itemAdded(SpeciesModel item, String key, int position) {
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
    }*/
}
