package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

public class Animations {

    //Github YoYo Animations uygulanması
    public static void yaziAnimasyonu(TextView soru, int i) {

        switch (i) {
            case 0:
                YoYo.with(Techniques.Tada).duration(1500).playOn(soru);
                break;
            case 1:
                YoYo.with(Techniques.RubberBand).duration(1500).playOn(soru);
                break;
            case 2:
                YoYo.with(Techniques.Pulse).duration(1500).playOn(soru);
                break;
            case 3:
                YoYo.with(Techniques.Shake).duration(1500).playOn(soru);
                break;
            case 4:
                YoYo.with(Techniques.Flash).duration(1500).playOn(soru);
                break;
            case 5:
                YoYo.with(Techniques.Swing).duration(1500).playOn(soru);
                break;
            case 6:
                YoYo.with(Techniques.Wobble).duration(1500).playOn(soru);
                break;
            case 7:
                YoYo.with(Techniques.Bounce).duration(1500).playOn(soru);
                break;
            case 8:
                YoYo.with(Techniques.StandUp).duration(1500).playOn(soru);
                break;
            case 9:
                YoYo.with(Techniques.Wave).duration(1500).playOn(soru);
                break;
            case 10:
                YoYo.with(Techniques.FlipInX).duration(1500).playOn(soru);
                break;
            case 11:
                YoYo.with(Techniques.BounceIn).duration(1500).playOn(soru);
                break;
            case 12:
                YoYo.with(Techniques.BounceInDown).duration(1500).playOn(soru);
                break;
            case 13:
                YoYo.with(Techniques.BounceInLeft).duration(1500).playOn(soru);
                break;
            case 14:
                YoYo.with(Techniques.BounceInRight).duration(1500).playOn(soru);
                break;
            case 15:
                YoYo.with(Techniques.BounceInUp).duration(1500).playOn(soru);
                break;
            case 16:
                YoYo.with(Techniques.FadeIn).duration(1500).playOn(soru);
                break;
            case 17:
                YoYo.with(Techniques.FadeInDown).duration(1500).playOn(soru);
                break;
            case 18:
                YoYo.with(Techniques.FadeInLeft).duration(1500).playOn(soru);
                break;
            case 19:
                YoYo.with(Techniques.FadeInRight).duration(1500).playOn(soru);
                break;
            case 20:
                YoYo.with(Techniques.FadeInUp).duration(1500).playOn(soru);
                break;
            case 21:
                YoYo.with(Techniques.FlipInY).duration(1500).playOn(soru);
                break;
            case 22:
                YoYo.with(Techniques.ZoomIn).duration(1500).playOn(soru);
                break;
            case 23:
                YoYo.with(Techniques.ZoomInDown).duration(1500).playOn(soru);
                break;
            case 24:
                YoYo.with(Techniques.ZoomInLeft).duration(1500).playOn(soru);
                break;
            case 25:
                YoYo.with(Techniques.ZoomInRight).duration(1500).playOn(soru);
                break;
            case 26:
                YoYo.with(Techniques.ZoomInUp).duration(1500).playOn(soru);
                break;
            case 27:
                YoYo.with(Techniques.SlideInDown).duration(1500).playOn(soru);
                break;
            case 28:
                YoYo.with(Techniques.SlideInLeft).duration(1500).playOn(soru);
                break;
            case 29:
                YoYo.with(Techniques.SlideInRight).duration(1500).playOn(soru);
                break;
            case 30:
                YoYo.with(Techniques.SlideInUp).duration(1500).playOn(soru);
                break;
        }

    }


    //Butonlara java diline ait olan animasyonun uygulanması
    public static void butonAnimasyonu(@NonNull List<View> butonlar) {

        AnimatorSet animatorSet = new AnimatorSet();

        //Animasyonun oluşturulması Y ekseninde sırayla gelmesi için birbirinden farklı
        ObjectAnimator objectAnimator0 = (ObjectAnimator) ObjectAnimator.ofInt(butonlar.get(0), "translationY", 500, 0);
        ObjectAnimator objectAnimator1 = (ObjectAnimator) ObjectAnimator.ofInt(butonlar.get(1), "translationY", 600, 0);
        ObjectAnimator objectAnimator2 = (ObjectAnimator) ObjectAnimator.ofInt(butonlar.get(2), "translationY", 700, 0);
        ObjectAnimator objectAnimator3 = (ObjectAnimator) ObjectAnimator.ofInt(butonlar.get(3), "translationY", 800, 0);

        //Animasyon süresi
        objectAnimator0.setDuration(1200);
        objectAnimator1.setDuration(1400);
        objectAnimator2.setDuration(1600);
        objectAnimator3.setDuration(1800);

        //Geçiş Animasyonu(Hızlanıp yavaşlama)
        objectAnimator0.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator3.setInterpolator(new AccelerateDecelerateInterpolator());

        //Animasyonları set edip başlat
        animatorSet.playTogether(objectAnimator0, objectAnimator1, objectAnimator2, objectAnimator3);
        animatorSet.start();

    }


    //Butona basma sonucu RelativeLayout titremesi
    public static void butonYanlışCevap(RelativeLayout sorularLayout) {

        YoYo.with(Techniques.Shake).duration(1500).playOn(sorularLayout);

    }


    //Butona basma sonucu random animasyonlar
    public static void tebrikAnimasyonu(TextView soru, int nextInt) {

        switch (nextInt) {
            case 0:
                YoYo.with(Techniques.Tada).duration(1500).playOn(soru);
                break;
            case 1:
                YoYo.with(Techniques.RubberBand).duration(1500).playOn(soru);
                break;
            case 2:
                YoYo.with(Techniques.Pulse).duration(1500).playOn(soru);
                break;
            case 3:
                YoYo.with(Techniques.Shake).duration(1500).playOn(soru);
                break;
            case 4:
                YoYo.with(Techniques.Flash).duration(1500).playOn(soru);
                break;
            case 5:
                YoYo.with(Techniques.Swing).duration(1500).playOn(soru);
                break;
            case 6:
                YoYo.with(Techniques.Wobble).duration(1500).playOn(soru);
                break;
            case 7:
                YoYo.with(Techniques.Bounce).duration(1500).playOn(soru);
                break;
            case 8:
                YoYo.with(Techniques.StandUp).duration(1500).playOn(soru);
                break;
            case 9:
                YoYo.with(Techniques.Wave).duration(1500).playOn(soru);
                break;
            case 10:
                YoYo.with(Techniques.FlipInX).duration(1500).playOn(soru);
                break;
            case 11:
                YoYo.with(Techniques.BounceIn).duration(1500).playOn(soru);
                break;
            case 12:
                YoYo.with(Techniques.BounceInDown).duration(1500).playOn(soru);
                break;
            case 13:
                YoYo.with(Techniques.BounceInLeft).duration(1500).playOn(soru);
                break;
            case 14:
                YoYo.with(Techniques.BounceInRight).duration(1500).playOn(soru);
                break;
            case 15:
                YoYo.with(Techniques.BounceInUp).duration(1500).playOn(soru);
                break;
            case 16:
                YoYo.with(Techniques.FadeIn).duration(1500).playOn(soru);
                break;
            case 17:
                YoYo.with(Techniques.FadeInDown).duration(1500).playOn(soru);
                break;
            case 18:
                YoYo.with(Techniques.FadeInLeft).duration(1500).playOn(soru);
                break;
            case 19:
                YoYo.with(Techniques.FadeInRight).duration(1500).playOn(soru);
                break;
            case 20:
                YoYo.with(Techniques.FadeInUp).duration(1500).playOn(soru);
                break;
            case 21:
                YoYo.with(Techniques.FlipInY).duration(1500).playOn(soru);
                break;
            case 22:
                YoYo.with(Techniques.ZoomIn).duration(1500).playOn(soru);
                break;
            case 23:
                YoYo.with(Techniques.ZoomInDown).duration(1500).playOn(soru);
                break;
            case 24:
                YoYo.with(Techniques.ZoomInLeft).duration(1500).playOn(soru);
                break;
            case 25:
                YoYo.with(Techniques.ZoomInRight).duration(1500).playOn(soru);
                break;
            case 26:
                YoYo.with(Techniques.ZoomInUp).duration(1500).playOn(soru);
                break;
            case 27:
                YoYo.with(Techniques.SlideInDown).duration(1500).playOn(soru);
                break;
            case 28:
                YoYo.with(Techniques.SlideInLeft).duration(1500).playOn(soru);
                break;
            case 29:
                YoYo.with(Techniques.SlideInRight).duration(1500).playOn(soru);
                break;
            case 30:
                YoYo.with(Techniques.SlideInUp).duration(1500).playOn(soru);
                break;

        }

    }


    //Soru sayısı animasyonu
    public static void soruSayıAnimasyonu(TextView soruSayi, int i) {
        switch (i) {
            case 0:
                YoYo.with(Techniques.ZoomIn).duration(1500).playOn(soruSayi);
                break;
        }
    }
}
