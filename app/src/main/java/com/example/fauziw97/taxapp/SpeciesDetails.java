package com.example.fauziw97.taxapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bogi on 25-Sep-17.
 */

public class SpeciesDetails extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage mStorage = FirebaseStorage.getInstance();
    StorageReference mStorageRef = mStorage.getReferenceFromUrl("gs://taxapp-c61c1.appspot.com/FotoAmfirep/");
    StorageReference refDorsal, refLateral, refOveral,refVentral;


    String name, speciesName;
    String circle = "\u25CF";
    @BindView(R.id.test)
    ImageView test;

    @BindView(R.id.speciesSlider)
    SliderLayout speciesSlider;
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvKingdom)
    TextView tvKingdom;
    @BindView(R.id.tvPhylum)
    TextView tvPhylum;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvFamily)
    TextView tvFamily;
    @BindView(R.id.tvGenus)
    TextView tvGenus;
    @BindView(R.id.tvIsiDeskripsi)
    TextView tvIsiDeskripsi;
    @BindView(R.id.tvIsiHabitat)
    TextView tvIsiHabitat;
    @BindView(R.id.tvIsiDistribute)
    TextView tvIsiDistribute;

 HashMap<String, String> image_url = new HashMap<String, String>();


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
            speciesName = name.replaceAll("[^A-Za-z]+", "");
        }




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*if (getSupportActionBar() != null) {
            if (name != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(name);
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }*/

        refDorsal = mStorageRef.child(speciesName + "D.png");
        refOveral = mStorageRef.child(speciesName + "O.png");
        refLateral = mStorageRef.child(speciesName + "L.png");
        refVentral = mStorageRef.child(speciesName + "V.png");

        getDownloadUrl();
        Glide.with(getApplicationContext())
                .using(new FirebaseImageLoader())
                .load(refOveral)
                .into(test);


        /*HashMap<String, String> image_url = new HashMap<String, String>();
        image_url.put("Overal", refOveral.toString());
        image_url.put("Dorsal", refDorsal.toString());
        image_url.put("Lateral", refLateral.toString());*/


    }

    public void slider() {

        for (String species : image_url.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(species)
                    .image(image_url.get(species))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", species);

            speciesSlider.addSlider(textSliderView);
        }

        speciesSlider.stopAutoCycle();
        speciesSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        speciesSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //speciesSlider.setCustomAnimation(new DescriptionAnimation());
        //speciesSlider.setDuration(4000);
        speciesSlider.addOnPageChangeListener(this);
    }

    private void getDownloadUrl() {
        refVentral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriDorsal = uri;
                image_url.put("Ventral", uriDorsal.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

        refDorsal.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriDorsal = uri;
                image_url.put("Dorsal", uriDorsal.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

        refLateral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriLateral = uri;
                image_url.put("Lateral", uriLateral.toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
        refOveral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriOveral = uri;
                image_url.put("Overal", uriOveral.toString());
                slider();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }



    @Override
    protected void onStop() {
        speciesSlider.stopAutoCycle();
        super.onStop();
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

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
