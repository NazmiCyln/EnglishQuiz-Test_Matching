package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Leveller.eslestir1;
import com.example.myapplication.Leveller.eslestir10;
import com.example.myapplication.Leveller.eslestir11;
import com.example.myapplication.Leveller.eslestir12;
import com.example.myapplication.Leveller.eslestir13;
import com.example.myapplication.Leveller.eslestir14;
import com.example.myapplication.Leveller.eslestir15;
import com.example.myapplication.Leveller.eslestir16;
import com.example.myapplication.Leveller.eslestir2;
import com.example.myapplication.Leveller.eslestir3;
import com.example.myapplication.Leveller.eslestir4;
import com.example.myapplication.Leveller.eslestir5;
import com.example.myapplication.Leveller.eslestir6;
import com.example.myapplication.Leveller.eslestir7;
import com.example.myapplication.Leveller.eslestir8;
import com.example.myapplication.Leveller.eslestir9;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class eslestir_bolumler extends AppCompatActivity {

    private AdView banner, banner1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eslestir_bolumler);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gecisReklam();

        //Banner oluşturma
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        banner = findViewById(R.id.adView);
        banner1 = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
        banner1.loadAd(adRequest);

    }

    public void testClick(View view) {

        switch (view.getId()){
            case R.id.eslestirLevel1:
                Intent eslestirIntent1 = new Intent(eslestir_bolumler.this, eslestir1.class);
                startActivity(eslestirIntent1);
                reklamBaslat();
                break;
            case R.id.eslestirLevel2:
                Intent eslestirIntent2 = new Intent(eslestir_bolumler.this, eslestir2.class);
                startActivity(eslestirIntent2);
                reklamBaslat();
                break;
            case R.id.eslestirLevel3:
                Intent eslestirIntent3 = new Intent(eslestir_bolumler.this, eslestir3.class);
                startActivity(eslestirIntent3);
                reklamBaslat();
                break;
            case R.id.eslestirLevel4:
                Intent eslestirIntent4 = new Intent(eslestir_bolumler.this, eslestir4.class);
                startActivity(eslestirIntent4);
                reklamBaslat();
                break;
            case R.id.eslestirLevel5:
                Intent eslestirIntent5 = new Intent(eslestir_bolumler.this, eslestir5.class);
                startActivity(eslestirIntent5);
                reklamBaslat();
                break;
            case R.id.eslestirLevel6:
                Intent eslestirIntent6 = new Intent(eslestir_bolumler.this, eslestir6.class);
                startActivity(eslestirIntent6);
                reklamBaslat();
                break;
            case R.id.eslestirLevel7:
                Intent eslestirIntent7 = new Intent(eslestir_bolumler.this, eslestir7.class);
                startActivity(eslestirIntent7);
                reklamBaslat();
                break;
            case R.id.eslestirLevel8:
                Intent eslestirIntent8 = new Intent(eslestir_bolumler.this, eslestir8.class);
                startActivity(eslestirIntent8);
                reklamBaslat();
                break;
            case R.id.eslestirLevel9:
                Intent eslestirIntent9 = new Intent(eslestir_bolumler.this, eslestir9.class);
                startActivity(eslestirIntent9);
                reklamBaslat();
                break;
            case R.id.eslestirLevel10:
                Intent eslestirIntent10 = new Intent(eslestir_bolumler.this, eslestir10.class);
                startActivity(eslestirIntent10);
                reklamBaslat();
                break;
            case R.id.eslestirLevel11:
                Intent eslestirIntent11 = new Intent(eslestir_bolumler.this, eslestir11.class);
                startActivity(eslestirIntent11);
                reklamBaslat();
                break;
            case R.id.eslestirLevel12:
                Intent eslestirIntent12 = new Intent(eslestir_bolumler.this, eslestir12.class);
                startActivity(eslestirIntent12);
                reklamBaslat();
                break;
            case R.id.eslestirLevel13:
                Intent eslestirIntent13 = new Intent(eslestir_bolumler.this, eslestir13.class);
                startActivity(eslestirIntent13);
                reklamBaslat();
                break;
            case R.id.eslestirLevel14:
                Intent eslestirIntent14 = new Intent(eslestir_bolumler.this, eslestir14.class);
                startActivity(eslestirIntent14);
                reklamBaslat();
                break;
            case R.id.eslestirLevel15:
                Intent eslestirIntent15 = new Intent(eslestir_bolumler.this, eslestir15.class);
                startActivity(eslestirIntent15);
                reklamBaslat();
                break;
            case R.id.eslestirLevel16:
                Intent eslestirIntent16 = new Intent(eslestir_bolumler.this, eslestir16.class);
                startActivity(eslestirIntent16);
                reklamBaslat();
                break;
        }
    }

    //Geçiş reklam başlat
    private void reklamBaslat() {
        if (mInterstitialAd!=null){
            mInterstitialAd.show(eslestir_bolumler.this);
            gecisReklam();}
    }

    //Geçiş reklam oluşturma
    public void gecisReklam() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-2396996479721834/6543991034", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }
                });
    }

}