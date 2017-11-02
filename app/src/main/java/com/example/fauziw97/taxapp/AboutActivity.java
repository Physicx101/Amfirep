package com.example.fauziw97.taxapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
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

    String urlFacebookBogi, urlGithubBogi;
    String urlFacebookTommy, urlGithubTommy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        urlFacebookBogi = "https://www.facebook.com/bogiwibowo";
        urlFacebookTommy = "https://www.facebook.com/tommy.zeroztoheroz";
        urlGithubBogi = "https://github.com/physicx101";
        urlGithubTommy = "https://github.com/tommywahyu44";

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tentang");


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
