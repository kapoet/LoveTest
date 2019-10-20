package com.ervin.lovetest;

import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getFragmentManager();
    private AdView mBannerAd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        ImageView v = (ImageView) findViewById(R.id.imageViewStart);
        final ImageView logo = (ImageView) findViewById(R.id.imageView);
        mBannerAd = (AdView) findViewById(R.id.banner_AdView);
        Picasso.with(this).load(R.drawable.logo).into(logo);
        Picasso.with(this).load(R.drawable.start).into(v);
        v.setVisibility(View.VISIBLE);

        showBannerAd();
        v.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                fm.beginTransaction().replace(R.id.content_frame, new loveTest()).commit();

            }
        });
    }

    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder()
              //  .addTestDevice("8666CDAEA108CB64A8FB3E34C5BCD1A4")
                .build();
        mBannerAd.loadAd(adRequest);
    }
}