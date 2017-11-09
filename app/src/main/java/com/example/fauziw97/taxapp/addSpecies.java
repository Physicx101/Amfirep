package com.example.fauziw97.taxapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fauziw97.taxapp.Model.FireBaseDataMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class addSpecies extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    private StorageReference mStorageRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static boolean changePhoto = false;
    String speciesName;
    static FireBaseDataMap obj = new FireBaseDataMap();
    static HashMap<String, String> dataMap = obj.fireebaseMap();
    String Jenis;
    static String JenisHewan;
    ProgressDialog progressDialog;

    EditText ETspesies;
    EditText ETkingdom;
    EditText ETphyllum;
    EditText ETordo;
    EditText ETclassis;
    EditText ETfamilia;
    EditText ETgenus;
    EditText ETStatusKonservasi;
    EditText ETdistribusi;
    EditText EThabitat;
    EditText ETdeskripsi;
    ImageView Overall;
    ImageView Dorsal;
    ImageView Ventral;
    ImageView Lateral;
    FloatingActionButton fabNext;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_species1);
        ETspesies = (EditText) findViewById(R.id.ETspesies);
        ETkingdom = (EditText) findViewById(R.id.ETKingdom);
        ETphyllum = (EditText) findViewById(R.id.ETPhyllum);
        ETclassis = (EditText) findViewById(R.id.ETClassis);
        ETordo = (EditText) findViewById(R.id.ETOrdo);
        ETfamilia = (EditText) findViewById(R.id.ETFamilia);
        ETgenus = (EditText) findViewById(R.id.ETGenus);

        fabNext = (FloatingActionButton) findViewById(R.id.fabnext);



        databaseReference = FirebaseDatabase.getInstance().getReference();


    }


    @OnClick(R.id.fabnext)
    public void fabNext(View view) {
        String spesies = ETspesies.getText().toString().trim();
        String classis = ETclassis.getText().toString().trim();
        String familia = ETfamilia.getText().toString().trim();
        String genus = ETgenus.getText().toString().trim();
        String ordo = ETordo.getText().toString().trim();
        String kingdom = ETkingdom.getText().toString().trim();
        String phyllum = ETphyllum.getText().toString().trim();


        if (TextUtils.isEmpty(spesies)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(genus)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phyllum)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(kingdom)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ordo)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(classis)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(familia)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }

        dataMap.put("Classis", classis);
        dataMap.put("Familia", familia);
        dataMap.put("Genus", genus);
        dataMap.put("Ordo", ordo);
        dataMap.put("Phylum", phyllum);
        dataMap.put("NamaSpesies", spesies);
        dataMap.put("Kingdom", kingdom);
        dataMap.put("Jenis", JenisHewan);

        speciesName = spesies.replaceAll("[^A-Za-z]+", "");

        setContentView(R.layout.activity_add_species);
    }

    @OnClick(R.id.fabaddspesies)
    public void fabAddSpesies(View view) {
        ETStatusKonservasi = (EditText) findViewById(R.id.ETStatusKonservasi);
        EThabitat = (EditText) findViewById(R.id.ETHabitat);
        ETdeskripsi = (EditText) findViewById(R.id.ETDeskripsi);
        ETdistribusi = (EditText) findViewById(R.id.ETDistribusi);

        fabAdd = (FloatingActionButton) findViewById(R.id.fabaddspesies);

        String status = ETStatusKonservasi.getText().toString().trim();
        String distribusi = ETdistribusi.getText().toString().trim();
        String habitat = EThabitat.getText().toString().trim();
        String deskripsi = ETdeskripsi.getText().toString().trim();

        if (TextUtils.isEmpty(status)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(distribusi)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(habitat)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(deskripsi)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_SHORT).show();
            return;
        }

        dataMap.put("StatusKonservasi", status);
        dataMap.put("Distribusi", distribusi);
        dataMap.put("Habitat", habitat);
        dataMap.put("Deskripsi", deskripsi);

        databaseReference.child("Amfirep").child(speciesName).setValue(dataMap);
        showWaitingBar();
        startActivity(new Intent(addSpecies.this, MainActivity.class));

    }

    @OnClick(R.id.Overall)
    public void Overall(View view) {

        Jenis = "O";
        uploadPicture();
    }

    @OnClick(R.id.Dorsal)
    public void Dorsal(View view) {

        Jenis = "D";
        uploadPicture();
    }

    @OnClick(R.id.Ventral)
    public void Ventral(View view) {

        Jenis = "V";
        uploadPicture();
    }

    @OnClick(R.id.Lateral)
    public void Lateral(View view) {

        Jenis = "L";
        uploadPicture();
    }


    public void uploadPicture() {


        final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Kembali"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Ambil gambar dari :");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Ambil Foto")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Pilih dari Galeri")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Kembali")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Overall = (ImageView) findViewById(R.id.Overall);
        Dorsal = (ImageView) findViewById(R.id.Dorsal);
        Ventral = (ImageView) findViewById(R.id.Ventral);
        Lateral = (ImageView) findViewById(R.id.Lateral);
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://taxapp-c61c1.appspot.com/FotoAmfirep/");

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {


                Bitmap mphoto = (Bitmap) data.getExtras().get("data");
//                if (Jenis.equals("O")) {
                    Overall.setImageBitmap(mphoto);
                    Overall.setMaxHeight(100);

//                } else if (Jenis.equals("D")) {
//                    Dorsal.setImageBitmap(mphoto);
//                    Dorsal.setMaxHeight(100);
//
//                } else if (Jenis.equals("V")) {
//                    Ventral.setImageBitmap(mphoto);
//                    Ventral.setMaxHeight(100);
//
//                } else if (Jenis.equals("L")) {
//                    Lateral.setImageBitmap(mphoto);
//                    Lateral.setMaxHeight(100);
//
//                }
//
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] dataimage = baos.toByteArray();
                StorageReference foto = mStorageRef.child(speciesName + Jenis + ".png");

                UploadTask uploadTask = foto.putBytes(dataimage);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        if (Jenis.equals("O")) {
                            dataMap.put("ImgOverall", taskSnapshot.getMetadata().getDownloadUrl().toString());
                        }
                    }
                });


            } else if (requestCode == 2) {


                Uri selectedImage = data.getData();


                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = this.getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);

                Log.w("Gambar dari Galeri", picturePath + "");
                if (Jenis.equals("O")) {
                    Overall.setImageBitmap(thumbnail);
                    Overall.setMaxHeight(100);

                } else if (Jenis.equals("D")) {
                    Dorsal.setImageBitmap(thumbnail);
                    Dorsal.setMaxHeight(100);

                } else if (Jenis.equals("V")) {
                    Ventral.setImageBitmap(thumbnail);
                    Ventral.setMaxHeight(100);

                } else if (Jenis.equals("L")) {
                    Lateral.setImageBitmap(thumbnail);
                    Lateral.setMaxHeight(100);

                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentText("Foto Telah Terupload");
                StorageReference foto = mStorageRef.child(speciesName + Jenis + ".png");
                UploadTask uploadTask = foto.putFile(selectedImage);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        if (Jenis.equals("O")) {
                            dataMap.put("ImgOverall", taskSnapshot.getMetadata().getDownloadUrl().toString());
                        }
                    }
                });
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage(((int) progress) + "% Uploaded .... ");
                        if (progress == 100) {
                            progressDialog.hide();
                        }
                    }
                })
                ;
            }
        }
    }

    public void showWaitingBar() {
        progressDialog = new ProgressDialog(addSpecies.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Membuat Spesies...");
        progressDialog.show();
    }

}


