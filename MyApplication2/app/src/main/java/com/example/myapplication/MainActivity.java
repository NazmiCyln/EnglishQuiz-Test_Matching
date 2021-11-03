package com.example.myapplication;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.cardview.widget.CardView;import androidx.core.app.ActivityCompat;import androidx.core.content.ContextCompat;import android.Manifest;import android.content.Intent;import android.content.pm.PackageManager;import android.os.Bundle;import android.view.View;import android.widget.Toast;import com.google.android.gms.ads.AdRequest;import com.google.android.gms.ads.AdView;import com.google.android.gms.ads.LoadAdError;import com.google.android.gms.ads.MobileAds;import com.google.android.gms.ads.initialization.InitializationStatus;import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;import com.google.android.gms.ads.interstitial.InterstitialAd;import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;public class MainActivity extends AppCompatActivity {    public static final String QUİZ_KEY="key";    public static final int COLOR=1;    public static final int ANİMAL=2;    public static final int NUMBER=3;    public static final int COUNTRY=4;    public static final int FILL=5;    public static final int WORD=6;    private CardView eslestirButton;    private AdView banner, banner1;    private InterstitialAd mInterstitialAd;    private static final int Permmission=1;    @Override    protected void onCreate(Bundle savedInstanceState) {        setTheme(R.style.Theme_AppCompat_NoActionBar);        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        gecisReklam();        //Banner oluşturma        MobileAds.initialize(this, new OnInitializationCompleteListener() {            @Override            public void onInitializationComplete(InitializationStatus initializationStatus) {            }        });        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED){            ActivityCompat.requestPermissions(MainActivity.this, new String[]                    {                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE                    },1            );        }        banner = findViewById(R.id.adView);        banner1 = findViewById(R.id.adView1);        AdRequest adRequest = new AdRequest.Builder().build();        banner.loadAd(adRequest);        banner1.loadAd(adRequest);        eslestirButton = findViewById(R.id.basla_eslestir);        eslestirButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent eslestirIntent = new Intent(MainActivity.this, eslestir_bolumler.class);                startActivity(eslestirIntent);                //reklamBaslat();            }        });    }    private void reklamBaslat() {        if (mInterstitialAd!=null){            mInterstitialAd.show(MainActivity.this);            gecisReklam();}    }    //Geçiş reklam oluşturma    public void gecisReklam(){        AdRequest adRequest = new AdRequest.Builder().build();        InterstitialAd.load(this,"ca-app-pub-2396996479721834/6543991034", adRequest,                new InterstitialAdLoadCallback() {                    @Override                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {                        mInterstitialAd = interstitialAd;                    }                });    }    public void testClick(View view) {        Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);        switch (view.getId()){            case R.id.basla_renk:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, COLOR);                startActivity(quizIntent);                break;            case R.id.basla_hayvan:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, ANİMAL);                startActivity(quizIntent);                break;            case R.id.basla_sayi:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, NUMBER);                startActivity(quizIntent);                break;            case R.id.basla_ulke:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, COUNTRY);                startActivity(quizIntent);                break;            case R.id.basla_doldur:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, FILL);                startActivity(quizIntent);                break;            case R.id.basla_kelime:                reklamBaslat();                quizIntent.putExtra(QUİZ_KEY, WORD);                startActivity(quizIntent);                break;        }    }}