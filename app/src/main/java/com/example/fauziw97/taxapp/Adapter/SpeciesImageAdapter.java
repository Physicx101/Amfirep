package com.example.fauziw97.taxapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fauziw97.taxapp.MainActivity;
import com.example.fauziw97.taxapp.Model.SpeciesImageModel;
import com.example.fauziw97.taxapp.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bogi on 23-Oct-17.
 */

public class SpeciesImageAdapter extends RecyclerView.Adapter<SpeciesImageAdapter.MyViewHolder> {
    List<SpeciesImageModel> horizontalList = Collections.emptyList();
    Context context;


    public SpeciesImageAdapter(List<SpeciesImageModel> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageSpecies) ImageView imageSpecies;
        @BindView(R.id.partSpecies) TextView partSpecies;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.species_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.imageSpecies.setImageResource(horizontalList.get(position).imageId);
        holder.partSpecies.setText(horizontalList.get(position).txt);

        holder.imageSpecies.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String list = horizontalList.get(position).txt.toString();
                Toast.makeText(context, list, Toast.LENGTH_SHORT).show();
            }

        });

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}
