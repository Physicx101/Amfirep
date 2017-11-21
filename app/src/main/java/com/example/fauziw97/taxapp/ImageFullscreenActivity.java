package com.example.fauziw97.taxapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.fauziw97.taxapp.Adapter.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageFullscreenActivity extends AppCompatActivity implements View.OnClickListener{
    TouchImageView image;
    ImageView back, downloadImage;
    FirebaseStorage storage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fullscreen);

        image = (TouchImageView)findViewById(R.id.image);
        back = (ImageView)findViewById(R.id.back);
        downloadImage = (ImageView) findViewById(R.id.forum_download);

        try{
            Intent data = getIntent();
            showProgressDialog();

            if(data.hasExtra("UrlImage")){
                Bundle extras = getIntent().getExtras();
                String address = extras.getString("UrlImage");
                fullScreen(address);

            }
        } catch (Exception e){
            Log.w("ShowImage.java", "There's error in get data:" + e.getMessage());
        }

        back.setOnClickListener(this);

        downloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){Calendar c = Calendar.getInstance();
                //create bitmap
                Bitmap bm;
                try{
                    image.buildDrawingCache();
                    bm=image.getDrawingCache();
                } catch (Exception e){
                    Toast.makeText(ImageFullscreenActivity.this, "Foto tidak tersedia", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                File rootPath = new File(Environment.getExternalStorageDirectory(), "TaxAppImage");
                if(!rootPath.exists()) {
                    rootPath.mkdirs();
                }

                File myDir = new File(rootPath + "/images");
                if(!myDir.exists()) {
                    myDir.mkdirs();
                }
                String fname = "Image-"+ formattedDate +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(ImageFullscreenActivity.this, "Foto berhasil diunduh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fullScreen (String src) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(src);

        File rootPath = new File(getFilesDir().getAbsolutePath(), "TaxAppImage");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"Image-HistoryFullScreen.png");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ",";local tem file created  created " +localFile.toString());
                File imgFile = new  File(getFilesDir().getAbsolutePath()+"/TaxAppImage/Image-HistoryFullScreen.png");

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    dismissProgressDialog();
                    image.setImageBitmap(myBitmap);

                }



                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
                File imgFile = new  File(getFilesDir().getAbsolutePath()+"/TaxAppImage/Image-HistoryFullScreen.png");

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    dismissProgressDialog();
                    image.setImageBitmap(myBitmap);

                }

            }

        });


    }

    @Override
    public void onClick(View v) {
        if(v == back){
            finish();
        }
    }


    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(ImageFullscreenActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
}
}
