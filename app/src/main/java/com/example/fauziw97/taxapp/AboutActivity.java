package com.example.fauziw97.taxapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.mailAbdul)
    LinearLayout mailAbdul;
    @BindView(R.id.facebookAbdul)
    LinearLayout facebookAbdul;
    @BindView(R.id.mailAji)
    LinearLayout mailAji;
    @BindView(R.id.facebookAji)
    LinearLayout facebookAji;
    @BindView(R.id.mailNishfi)
    LinearLayout mailNishfi;
    @BindView(R.id.facebookNisfhi)
    LinearLayout facebookNishfi;
    @BindView(R.id.mailNoor)
    LinearLayout mailNoor;
    @BindView(R.id.facebookNoor)
    LinearLayout facebookNoor;

    @BindView(R.id.mailBogi)
    LinearLayout mailBogi;
    @BindView(R.id.facebookBogi)
    LinearLayout facebookBogi;
    @BindView(R.id.githubBogi)
    LinearLayout githubBogi;
    @BindView(R.id.mailTommy)
    LinearLayout mailTommy;
    @BindView(R.id.facebookTommy)
    LinearLayout facebookTommy;
    @BindView(R.id.githubTommy)
    LinearLayout githubTommy;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_Abdul)
    CircleImageView ivAbdul;
    @BindView(R.id.iv_Aji)
    CircleImageView ivAji;
    @BindView(R.id.iv_Nishfi)
    CircleImageView ivNishfi;
    @BindView(R.id.iv_Noor)
    CircleImageView ivNoor;
    @BindView(R.id.iv_Bogi)
    CircleImageView ivBogi;
    @BindView(R.id.iv_Tommy)
    CircleImageView ivTommy;

    String urlFacebookBogi, urlGithubBogi;
    String urlFacebookTommy, urlGithubTommy;
    String urlFacebookAbdul, urlFacebookAji, urlFacebookNishfi, urlFacebookNoor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        urlFacebookAbdul = "https://www.facebook.com/afattah19";
        urlFacebookAji = "https://www.facebook.com/ajipamula.gunawanputra";
        urlFacebookNishfi = "https://www.facebook.com/nishfi.laila";
        urlFacebookBogi = "https://www.facebook.com/bogiwibowo";
        urlFacebookTommy = "https://www.facebook.com/tommy.zeroztoheroz";
        urlGithubBogi = "https://github.com/physicx101";
        urlGithubTommy = "https://github.com/tommywahyu44";

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tentang");

        Glide.with(this)
                .load(R.drawable.abdul)
                .into(ivAbdul);
        Glide.with(this)
                .load(R.drawable.aji)
                .into(ivAji);
        Glide.with(this)
                .load(R.drawable.nishfi)
                .into(ivNishfi);
        Glide.with(this)
                .load(R.drawable.noor)
                .into(ivNoor);
        Glide.with(this)
                .load(R.drawable.bogi)
                .into(ivBogi);
        Glide.with(this)
                .load(R.drawable.tommy)
                .into(ivTommy);
    }

    @OnClick({R.id.mailAbdul, R.id.facebookAbdul, R.id.mailAji, R.id.facebookAji, R.id.mailNishfi, R.id.facebookNisfhi, R.id.mailNoor})
    public void linkTeam(View view) {
        switch (view.getId()) {
            case R.id.mailAbdul:
                Intent intentMailAbdul = new Intent(Intent.ACTION_SENDTO);
                intentMailAbdul.setData(Uri.parse("mailto:afattah1996@gmail.com"));
                startActivity(intentMailAbdul);
                break;

            case R.id.facebookAbdul:
                Intent intentFbAbdul = new Intent(Intent.ACTION_VIEW);
                intentFbAbdul.setData(Uri.parse(urlFacebookAbdul));
                startActivity(intentFbAbdul);
                break;

            case R.id.mailAji:
                Intent intentMailAji = new Intent(Intent.ACTION_SENDTO);
                intentMailAji.setData(Uri.parse("mailto:gttaji@yahoo.com"));
                startActivity(intentMailAji);
                break;

            case R.id.facebookAji:
                Intent intentFbAji = new Intent(Intent.ACTION_VIEW);
                intentFbAji.setData(Uri.parse(urlFacebookAji));
                startActivity(intentFbAji);
                break;

            case R.id.mailNishfi:
                Intent intentMailNishfi = new Intent(Intent.ACTION_SENDTO);
                intentMailNishfi.setData(Uri.parse("mailto:lailanishfi@gmail.com"));
                startActivity(intentMailNishfi);
                break;

            case R.id.facebookNisfhi:
                Intent intentFbNishfi = new Intent(Intent.ACTION_VIEW);
                intentFbNishfi.setData(Uri.parse(urlFacebookNishfi));
                startActivity(intentFbNishfi);
                break;

            case R.id.mailNoor:
                Intent intentMailNoor = new Intent(Intent.ACTION_VIEW);
                intentMailNoor.setData(Uri.parse("mailto:noor.laina.m@gmail.com"));
                startActivity(intentMailNoor);
                break;
        }
    }

    @OnClick({R.id.mailBogi, R.id.facebookBogi, R.id.githubBogi})
    public void linkBogi(View view) {
        switch (view.getId()) {
            case R.id.mailBogi:
                Intent intentMail = new Intent(Intent.ACTION_SENDTO);
                intentMail.setData(Uri.parse("mailto:ahmadfauziw97@gmail.com"));
                startActivity(intentMail);
                break;

            case R.id.facebookBogi:
                Intent intentFb = new Intent(Intent.ACTION_VIEW);
                intentFb.setData(Uri.parse(urlFacebookBogi));
                startActivity(intentFb);
                break;

            case R.id.githubBogi:
                Intent intentGit = new Intent(Intent.ACTION_VIEW);
                intentGit.setData(Uri.parse(urlGithubBogi));
                startActivity(intentGit);
                break;
        }
    }

    @OnClick({R.id.mailTommy, R.id.facebookTommy, R.id.githubTommy})
    public void linkTommy(View view) {
        switch (view.getId()) {
            case R.id.mailTommy:
                Intent intentMail = new Intent(Intent.ACTION_SENDTO);
                intentMail.setData(Uri.parse("mailto:tommywahyu44@gmail.com"));
                startActivity(intentMail);
                break;

            case R.id.facebookTommy:
                Intent intentFb = new Intent(Intent.ACTION_VIEW);
                intentFb.setData(Uri.parse(urlFacebookTommy));
                startActivity(intentFb);
                break;

            case R.id.githubTommy:
                Intent intentGit = new Intent(Intent.ACTION_VIEW);
                intentGit.setData(Uri.parse(urlGithubTommy));
                startActivity(intentGit);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
