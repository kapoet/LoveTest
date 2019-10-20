package com.ervin.lovetest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

/**
 * Created by ervin on 09/08/16.
 */
public class result extends Activity implements View.OnClickListener{
    private InterstitialAd mInterstitialAd;
    boolean doubleBackToExitPressedOnce = false;
    Button restart;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Bundle extras = getIntent().getExtras();
        int a= extras.getInt("hasil");
        ImageView v = (ImageView) findViewById(R.id.imageView2);
        TextView result = (TextView) findViewById(R.id.textView3);
        TextView tv = (TextView) findViewById(R.id.textView2);
        restart = (Button) findViewById(R.id.button) ;
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        Typeface face= Typeface.createFromAsset(getAssets(), "font/JandaRomantic.ttf");

        tv.setTypeface(face);
        String hasil = String.valueOf(a);
        result.setText(hasil+"%");

        if (a<=50){
            tv.setText("Don't give up!! You still have a chance");
            Picasso.with(this).load(R.drawable.heart50).into(v);
        } else if (a<=75 && a>=51){
            tv.setText("Your life will be full with love!");
            Picasso.with(this).load(R.drawable.heart75).into(v);
        } else if (a<=100 && a>=76){
            tv.setText("Your majestic love will last forever");
            Picasso.with(this).load(R.drawable.love_new).into(v);
        }

        restart.setOnClickListener(this);
    }

    private void showIntAdd() {

// Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
           finish();
        }
    }
    private void loadIntAdd() {
        AdRequest adRequest = new AdRequest.Builder()
          //      .addTestDevice("8666CDAEA108CB64A8FB3E34C5BCD1A4")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        showIntAdd();

    }

    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                finish();
            }
        });
        return intAd;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
