package com.example.myapplication.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Tasarı;
import com.example.myapplication.eslestir_bolumler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Fragment11 extends Fragment {

    private Context context;
    private List<Tasarı> solBölümList, sağBölümList;
    private int widthButton, heightButton;
    private LinearLayout solLayout, sağLayout;
    private int x, y, silinenButonSayısı = 0;
    private static final int BEKLE = 500, BUTON_KAYBOL = 300;
    private Button seçiliButonSol, seçiliButonSağ;
    private boolean buttonClickEnabled = true;
    private Handler handler;

    private InterstitialAd mInterstitialAd;


    private View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            x = (int) motionEvent.getX();
            y = (int) motionEvent.getY();

            return false;
        }
    };


    private View.OnClickListener clickListenerSol = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //buton false ise geri dön
            if (!buttonClickEnabled)
                return;

            //Eşleştirme kontrolü
            if (seçiliButonSağ != null) {

                seçiliButonSol = (Button) view;
                buttonClickEnabled = false;

                if (seçiliButonSol.getId() == seçiliButonSağ.getId()) {
                    dogruEfect();
                } else {
                    yanlışEfect();
                }
            } else {
                if (seçiliButonSol != null) {
                    seçiliButonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonPressSol)));
                }

                seçiliButonSol = (Button) view;
                seçiliButonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonPressSağ)));
            }
        }
    };


    private View.OnClickListener clickListenerSağ = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //buton false ise geri dön
            if (!buttonClickEnabled)
                return;

            //Eşleştirme kontrolü
            if (seçiliButonSol != null) {

                seçiliButonSağ = (Button) view;
                buttonClickEnabled = false;

                if (seçiliButonSol.getId() == seçiliButonSağ.getId()) {
                    dogruEfect();
                } else {
                    yanlışEfect();
                }
            } else {
                if (seçiliButonSağ != null) {
                    seçiliButonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonPressSağ)));
                }

                seçiliButonSağ = (Button) view;
                seçiliButonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonPressSol)));

            }

        }
    };

    private void yanlışEfect() {

        //Butonun genişliğine göre efekt sınırı belirliyoruz
        int finalRadius = Math.max(widthButton, heightButton);

        //Sağ ve sol layouta farklı farklı animasyonlar oluşturuluyor
        Animator animatorSağ = ViewAnimationUtils.createCircularReveal(seçiliButonSağ, x, y, 0, finalRadius);
        Animator animatorSol = ViewAnimationUtils.createCircularReveal(seçiliButonSol, x, y, 0, finalRadius);

        //Animations rengi
        seçiliButonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonYanlis)));
        seçiliButonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonYanlis)));

        //Animations bittiğinde yapılacaklar
        animatorSol.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                seçiliButonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSağ)));
                seçiliButonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSol)));

                seçiliButonSağ = null;
                seçiliButonSol = null;

                buttonClickEnabled = true;
            }
        });
        animatorSağ.setDuration(BEKLE);
        animatorSol.setDuration(BEKLE);
        animatorSağ.start();
        animatorSol.start();
    }

    private void dogruEfect() {

        //Butonun genişliğine göre efekt sınırı belirliyoruz
        int finalRadius = Math.max(widthButton, heightButton);

        //sağ ve sol layouta farklı farklı animasyonlar oluşturuluyor
        Animator animatorSağ = ViewAnimationUtils.createCircularReveal(seçiliButonSağ, x, y, 0, finalRadius);
        Animator animatorSol = ViewAnimationUtils.createCircularReveal(seçiliButonSol, x, y, 0, finalRadius);

        //animations rengi
        seçiliButonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonDogru)));
        seçiliButonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonDogru)));

        //Animations bittiğinde yapılacaklar
        animatorSol.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yokOl();

            }
        });
        animatorSağ.setDuration(BEKLE);
        animatorSol.setDuration(BEKLE);
        animatorSağ.start();
        animatorSol.start();

    }

    private void yokOl() {

        handler = new Handler();

        //Animasyon
        ObjectAnimator animatorSol = ObjectAnimator.ofFloat(seçiliButonSol, "TranslationX", 0, -widthButton * 1.5f);
        ObjectAnimator animatorSağ = ObjectAnimator.ofFloat(seçiliButonSağ, "TranslationX", 0, widthButton * 1.5f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorSağ, animatorSol);
        set.setDuration(BUTON_KAYBOL);
        set.setInterpolator(new LinearInterpolator());

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                seçiliButonSol.setVisibility(View.GONE);
                seçiliButonSağ.setVisibility(View.GONE);
                seçiliButonSol = null;
                seçiliButonSağ = null;

                buttonClickEnabled = true;

                silinenButonSayısı++;

                if (solBölümList.size() == 0 && sağBölümList.size() == 0) {

                    //Oyun bitti
                    if (silinenButonSayısı == 16) {
                        //Custom Dialog
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog();
                            }
                        }, 0);
                    }
                } else {
                    butonEkle();
                }
            }
        });
        set.start();

    }

    private void dialog() {

        new AlertDialog.Builder(context).setTitle("Good Job!")
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.cancel();
                    }
                }).show();
    }

    //Geçiş reklam başlat
    private void reklamBaslat() {
        if (mInterstitialAd!=null){
            mInterstitialAd.show((Activity) Fragment11.this.context);
            gecisReklam();}
    }

    //Geçiş reklam oluşturma
    public void gecisReklam() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(Fragment11.this.context,"ca-app-pub-2396996479721834/6543991034", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }
                });
    }

    private void finish() {

        Intent eslestirBolumIntent = new Intent(Fragment11.this.context, eslestir_bolumler.class);
        startActivity(eslestirBolumIntent);
        reklamBaslat();
    }
    private void butonEkle() {

        //Yeni eklenecek butonlar Sol
        Button buttonSol = new Button(context);
        buttonSol.setWidth(widthButton);
        buttonSol.setHeight(heightButton);
        buttonSol.setTextColor(getResources().getColor(R.color.Ana_Sayfa_YazıRenk));
        buttonSol.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSol)));
        buttonSol.setAllCaps(false);
        buttonSol.setTextSize(heightButton / 18);
        buttonSol.setId(solBölümList.get(0).getId());
        buttonSol.setText(solBölümList.get(0).getKelime());
        buttonSol.setOnTouchListener(touchListener);
        buttonSol.setOnClickListener(clickListenerSol);
        solBölümList.remove(0);
        solLayout.addView(buttonSol, 0);

        //Yeni eklenecek butonların animasyonları
        ObjectAnimator animatorSol = ObjectAnimator.ofFloat(buttonSol, "TranslationX", -widthButton * 1.5f, 0);
        animatorSol.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSol.setDuration(700);
        animatorSol.start();


        //Yeni eklenecek butonlar Sağ
        Button buttonSağ = new Button(context);
        buttonSağ.setWidth(widthButton);
        buttonSağ.setHeight(heightButton);
        buttonSağ.setTextColor(getResources().getColor(R.color.eslestirButonYazıSağ));
        buttonSağ.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSağ)));
        buttonSağ.setAllCaps(false);
        buttonSağ.setTextSize(heightButton / 18);
        buttonSağ.setId(sağBölümList.get(0).getId());
        buttonSağ.setText(sağBölümList.get(0).getAnlam());
        buttonSağ.setOnTouchListener(touchListener);
        buttonSağ.setOnClickListener(clickListenerSağ);
        sağBölümList.remove(0);
        sağLayout.addView(buttonSağ, 0);

        //Yeni eklenecek butonların animasyonları
        ObjectAnimator animatorSağ = ObjectAnimator.ofFloat(buttonSağ, "TranslationX", widthButton * 1.5f, 0);
        animatorSağ.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSağ.setDuration(700);
        animatorSağ.start();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_11, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gecisReklam();

        solBölümList = new ArrayList<>();
        sağBölümList = new ArrayList<>();

        solLayout = ((AppCompatActivity) context).findViewById(R.id.layoutSol);
        sağLayout = ((AppCompatActivity) context).findViewById(R.id.layoutSağ);


        //Buton genişlik ve yükseklik ekrana göre ayarlanıyor
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        widthButton = (int) ((point.x) / (2 + .3));
        heightButton = (int) ((point.y) / (7.5 + 1.7));

        solBölümList.add(new Tasarı("Nurse", "Hemşire", 0));
        solBölümList.add(new Tasarı("Submarine", "Denizaltı ", 1));
        solBölümList.add(new Tasarı("Monument", "Anıt", 2));
        solBölümList.add(new Tasarı("Hospital", "Hastane", 3));
        solBölümList.add(new Tasarı("Schedule", "Program", 4));
        solBölümList.add(new Tasarı("Experiment", "Deney", 5));
        solBölümList.add(new Tasarı("Library", "Kütüphane", 6));
        solBölümList.add(new Tasarı("Subway", "Metro", 7));
        solBölümList.add(new Tasarı("Town", "Kasaba", 8));
        solBölümList.add(new Tasarı("Castle", "Kale", 9));
        solBölümList.add(new Tasarı("Gift", "Hediye", 10));
        solBölümList.add(new Tasarı("Customer", "Müşteri", 11));
        solBölümList.add(new Tasarı("Broadcast", "Yayın", 12));
        solBölümList.add(new Tasarı("Newspaper", "Gazete", 13));
        solBölümList.add(new Tasarı("Journalist", "Gazeteci", 14));
        solBölümList.add(new Tasarı("Chain", "Zincir", 15));

        //Klonlama
        for (Tasarı tasarı : solBölümList) {
            sağBölümList.add(tasarı);
        }

        Collections.shuffle(solBölümList);
        Collections.shuffle(sağBölümList);


        //Soldaki butonlar
        for (int i = 0; i < 7; i++) {

            Button button = new Button(context);
            button.setWidth(widthButton);
            button.setHeight(heightButton);
            button.setTextColor(getResources().getColor(R.color.Ana_Sayfa_YazıRenk));
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSol)));
            button.setAllCaps(false);
            button.setTextSize(heightButton / 18);
            button.setId(solBölümList.get(0).getId());
            button.setText(solBölümList.get(0).getKelime());
            button.setOnTouchListener(touchListener);
            button.setOnClickListener(clickListenerSol);
            solBölümList.remove(0);
            solLayout.addView(button);
        }

        //Sağdaki butonlar
        for (int i = 0; i < 7; i++) {

            Button button = new Button(context);
            button.setWidth(widthButton);
            button.setHeight(heightButton);
            button.setTextColor(getResources().getColor(R.color.eslestirButonYazıSağ));
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eslestirButonSağ)));
            button.setAllCaps(false);
            button.setTextSize(heightButton / 18);
            button.setId(sağBölümList.get(0).getId());
            button.setText(sağBölümList.get(0).getAnlam());
            button.setOnTouchListener(touchListener);
            button.setOnClickListener(clickListenerSağ);
            sağBölümList.remove(0);
            sağLayout.addView(button);
        }

        girisAnimasyon();

    }


    private void girisAnimasyon() {

        for (int i = 0; i < solLayout.getChildCount(); i++) {
            Button button = (Button) solLayout.getChildAt(5 - i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(button, "translationX", -widthButton * 1.5f, 0);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(BEKLE + i * 70);
            animator.start();
        }

        for (int i = 0; i < sağLayout.getChildCount(); i++) {
            Button button = (Button) sağLayout.getChildAt(5 - i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(button, "translationX", widthButton * 1.5f, 0);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(BEKLE + i * 70);
            animator.start();
        }

    }
}