package com.example.fauziw97.taxapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fauziw97.taxapp.Adapter.SpeciesImageAdapter;
import com.example.fauziw97.taxapp.Model.SpeciesImageModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;

/**
 * Created by Bogi on 25-Sep-17.
 */

public class SpeciesDetails extends AppCompatActivity {

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    String name, speciesName;
    String circle = "\u25CF";
    private List<SpeciesImageModel> data;
    private RecyclerView.Adapter mAdapter;
    RecyclerView horizontal_recycler_view;
    @BindView(R.id.tvNama) TextView tvNama;
    @BindView(R.id.tvStatus) TextView tvStatus;
    @BindView(R.id.tvKingdom) TextView tvKingdom;
    @BindView(R.id.tvPhylum) TextView tvPhylum;
    @BindView(R.id.tvClass) TextView tvClass;
    @BindView(R.id.tvOrder) TextView tvOrder;
    @BindView(R.id.tvFamily) TextView tvFamily;
    @BindView(R.id.tvGenus) TextView tvGenus;
    @BindView(R.id.tvIsiDeskripsi) TextView tvIsiDeskripsi;
    @BindView(R.id.tvIsiHabitat) TextView tvIsiHabitat;
    @BindView(R.id.tvIsiDistribute) TextView tvIsiDistribute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_species);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("speciesName");
            speciesName = name.replaceAll("[^A-Za-z]+","");
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*if (getSupportActionBar() != null) {
            if (name != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(name);
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }*/




        horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        data = fill_with_data();


        mAdapter = new SpeciesImageAdapter(data, getApplicationContext());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(mAdapter);
    }

        public List<SpeciesImageModel> fill_with_data() {

            List<SpeciesImageModel> data = new ArrayList<>();

            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 1"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 2"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 3"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 1"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 2"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 3"));
            data.add(new SpeciesImageModel( R.drawable.komodo, "Image 1"));


            return data;
        }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference ref = mRef.child("Amfirep").child(speciesName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNama.setText(dataSnapshot.child("NamaSpesies").getValue().toString());
                tvStatus.setText(dataSnapshot.child("StatusKonservasi").getValue().toString());
                tvKingdom.setText(circle + " " + "Kingdom: " + dataSnapshot.child("Kingdom").getValue().toString());
                tvPhylum.setText(circle + " " + "Phylum: " + dataSnapshot.child("Phylum").getValue().toString());
                tvClass.setText(circle + " " + "Classis: " + dataSnapshot.child("Classis").getValue().toString());
                tvOrder.setText(circle + " " + "Ordo: " + dataSnapshot.child("Ordo").getValue().toString());
                tvFamily.setText(circle + " " + "Familia: " + dataSnapshot.child("Familia").getValue().toString());
                tvGenus.setText(circle + " " + "Genus: " + dataSnapshot.child("Genus").getValue().toString());
                tvIsiDeskripsi.setText(dataSnapshot.child("Deskripsi").getValue().toString());
                tvIsiHabitat.setText(dataSnapshot.child("Habitat").getValue().toString());
                tvIsiDistribute.setText(dataSnapshot.child("Distribusi").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
