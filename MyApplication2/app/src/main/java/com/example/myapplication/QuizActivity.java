package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    private final int TOPLAM_SORU_SAYISI = 10;
    private List<String> genelListe, soruListesi;
    private RelativeLayout sorularLayout;
    private LinearLayout butonlarLayout;
    private TextView soruSayi, soru;
    private Random random;
    private int quiz, toplamCevapSayi, dogruCevapSayi, denemeSayisi;
    private Handler handler;
    private String dogruCevap;
    private TextView textSure;
    public int counter;

    private AdView banner;
    private InterstitialAd mInterstitialAd;

    private View.OnClickListener butonTakip = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            toplamCevapSayi++;
            Button button = (Button) view;
            String verilenCevap = button.getText().toString();

            //Verilen cevap doğruysa
            if (verilenCevap.equals(dogruCevap)){
                ekranMesaj(denemeSayisi);
                denemeSayisi=1;
                butonlarPasif();
                dogruCevapSayi++;

                if (dogruCevapSayi==TOPLAM_SORU_SAYISI){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog();
                        }
                    },1500);
                }
                else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sonrakiSoru();
                        }
                    },2000);
                }
            }
            //Verilen cevap yanlışsa
            else {
                denemeSayisi++;
                button.setEnabled(false);
                Animations.butonYanlışCevap(sorularLayout);

            }
        }
    };

    private void dialog() {

        new AlertDialog.Builder(QuizActivity.this).setTitle("Finished!")
                .setMessage(getResources().getString(R.string.skor,(float)(1000/toplamCevapSayi)))
                .setPositiveButton("Test Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetQuiz();
                        reklamBaslat();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        reklamBaslat();
                        dialogInterface.cancel();
                    }
                }).show();

    }


    //Geçiş reklam başlat
    private void reklamBaslat() {
        if (mInterstitialAd!=null){
            mInterstitialAd.show(QuizActivity.this);
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


    //Soru cevaplanınca ekrana yazdırılacak yazı ve animasyonu
    private void ekranMesaj(int denemeSayisi) {

        String mesaj="";

        switch (denemeSayisi){
            case 1:
                mesaj="Legendary!";
                break;
            case 2:
                mesaj="Good!";
                break;
            case 3:
                mesaj="Not Bad!";
                break;
            case 4:
                mesaj="Finally!";
                break;
        }
        //Yazı renk, boyutu ve animasyonu
        soru.setText(mesaj);
        soru.setTextColor(Color.GREEN);
        soru.setTextSize(40);
        Animations.tebrikAnimasyonu(soru, random.nextInt(20));

    }

    private void butonlarPasif() {

        for (int satir=0; satir<butonlarLayout.getChildCount(); satir++) {
            Button button = (Button) butonlarLayout.getChildAt(satir);
            button.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gecisReklam();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        banner = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        quiz = getIntent().getIntExtra(MainActivity.QUİZ_KEY, MainActivity.COLOR);

        genelListe = new ArrayList<String>();
        soruListesi = new ArrayList<String>();

        random = new Random();
        handler = new Handler();
        denemeSayisi=1;

        sorularLayout=findViewById(R.id.relative);
        butonlarLayout=findViewById(R.id.buttonlar);
        soruSayi=findViewById(R.id.textView_Soru_Sayı);
        soru=findViewById(R.id.textView_Soru);

        //Buton takibi
        for (int satir=0; satir<butonlarLayout.getChildCount(); satir++){
            Button button = (Button) butonlarLayout.getChildAt(satir);
            button.setOnClickListener(butonTakip);
        }

        soruSayi.setText("1 / " +TOPLAM_SORU_SAYISI);

        switch (quiz){
            case MainActivity.COLOR:
                soru.setTextSize(30);
                String[] dizi1 = getResources().getStringArray(R.array.renkler);
                for (String string:dizi1){
                    genelListe.add(string);
                }
                break;
            case MainActivity.ANİMAL:
                soru.setTextSize(30);
                String[] dizi2 = getResources().getStringArray(R.array.hayvanlar);
                for (String string:dizi2){
                    genelListe.add(string);
                }
                break;
            case MainActivity.NUMBER:
                String[] dizi3 = getResources().getStringArray(R.array.sayilar);
                for (String string:dizi3){
                    genelListe.add(string);
                }
                break;
            case MainActivity.COUNTRY:
                soru.setTextSize(30);
                String[] dizi4 = getResources().getStringArray(R.array.ulkeler);
                for (String string:dizi4){
                    genelListe.add(string);
                }
                break;
            case MainActivity.FILL:
                soru.setTextSize(15);
                String[] dizi5 = getResources().getStringArray(R.array.doldur);
                for (String string:dizi5){
                    genelListe.add(string);
                }
                break;
            case MainActivity.WORD:
                soru.setTextSize(35);
                String[] dizi6 = getResources().getStringArray(R.array.kelimeler);
                for (String string:dizi6){
                    genelListe.add(string);
                }
                break;
        }

        resetQuiz();

    }

    private void resetQuiz() {
        toplamCevapSayi=0;
        dogruCevapSayi=0;

        soruListesi.clear();

        int soruSayisi=1;
        int genelListeSoruSayi=genelListe.size();
        //Rastgele 10 farklı soru seçip soru listesine ekle
        while (soruSayisi<=TOPLAM_SORU_SAYISI){
            int index = random.nextInt(genelListeSoruSayi);
            String soru=genelListe.get(index);

            if (!soruListesi.contains(soru)) {
                soruListesi.add(soru);
                soruSayisi++;
            }
        }

        sonrakiSoru();
    }

    private void sonrakiSoru() {
        if (quiz==MainActivity.COLOR){
            soru.setTextSize(35);
        }
        if (quiz==MainActivity.ANİMAL){
            soru.setTextSize(35);
        }
        if (quiz==MainActivity.NUMBER){
            soru.setTextSize(55);
        }
        if (quiz==MainActivity.COUNTRY){
            soru.setTextSize(35);
        }
        if (quiz==MainActivity.FILL){
            soru.setTextSize(20);
        }
        if (quiz==MainActivity.WORD){
            soru.setTextSize(35);
        }
        soru.setTextColor(Color.parseColor("#000000"));
        soruSayi.setText(dogruCevapSayi+1 +" / "+ TOPLAM_SORU_SAYISI);



        String sorun = soruListesi.remove(0);
        soru.setText(getSoru(sorun));
        dogruCevap = getCevap(sorun);

        //Butonların belirlenmesi
        Collections.shuffle(genelListe);

        int dogruCevabinIndisi = genelListe.indexOf(sorun);
        //Doğru cevabı alıp listenin sonuna eklenmesi
        genelListe.add(genelListe.remove(dogruCevabinIndisi));


        //Döngü ile butonların rastgele doldurulması
        for (int satir=0; satir<butonlarLayout.getChildCount(); satir++){
            Button button= (Button) butonlarLayout.getChildAt(satir);
            String secenek = genelListe.get(satir);
            button.setText(getCevap(secenek));
            button.setEnabled(true);
        }


        //Doğru cevap rastgele bir butona ekleniyor
        int rastgeleIndis=random.nextInt(4);
        ((Button)butonlarLayout.getChildAt(rastgeleIndis)).setText(dogruCevap);

        soruAnimasyonu();
    }

    private void soruAnimasyonu() {

        List<View> butonlar = new ArrayList<>();

        //Butonlara erişim
        for (int satir=0; satir<butonlarLayout.getChildCount(); satir++){
            Button button= (Button) butonlarLayout.getChildAt(satir);
            butonlar.add(button);
        }

        Animations.yaziAnimasyonu(soru, random.nextInt(31));
        Animations.soruSayıAnimasyonu(soruSayi, random.nextInt(1));
        Animations.butonAnimasyonu(butonlar);

    }

    //Arraydaki soru ve cevaplardaki - işaretinden öncesini al
    private String getSoru(String sorun) {

        sorun = sorun.substring(0, sorun.indexOf("-"));
        return sorun;
    }

    // Arraydaki soru ve cevaplardaki - işaretinden sonrasını al
    private String getCevap(String sorun) {

      sorun = sorun.substring(sorun.indexOf("-")+1,sorun.length());
      return sorun;
    }

}