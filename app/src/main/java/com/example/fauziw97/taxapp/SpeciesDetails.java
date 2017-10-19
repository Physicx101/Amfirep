package com.example.fauziw97.taxapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bogi on 25-Sep-17.
 */

public class SpeciesDetails extends AppCompatActivity {

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    String name, speciesName;
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
        if (getSupportActionBar() != null) {
            if (name != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(name);
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }


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
                tvKingdom.setText("Kingdom: " + dataSnapshot.child("Kingdom").getValue().toString());
                tvPhylum.setText("Phylum: " + dataSnapshot.child("Phylum").getValue().toString());
                tvClass.setText("Classis: " + dataSnapshot.child("Classis").getValue().toString());
                tvOrder.setText("Ordo: " + dataSnapshot.child("Ordo").getValue().toString());
                tvFamily.setText("Familia: " + dataSnapshot.child("Familia").getValue().toString());
                tvGenus.setText("Genus: " + dataSnapshot.child("Genus").getValue().toString());
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
