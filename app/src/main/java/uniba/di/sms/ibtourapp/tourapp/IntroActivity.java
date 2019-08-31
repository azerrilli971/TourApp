package uniba.di.sms.ibtourapp.tourapp;


import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

public class IntroActivity extends AppIntro2 {

    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Dichiarazione fragment utilizzati per il tutorial
        Fragment firstFragment;
        Fragment secondFragment;
        Fragment thirdFragment;
        Fragment fourthFragment;

        //Creazione e aggiunta allo slider dei fragment

        secondFragment = AppIntro2Fragment.newInstance(getString(R.string.tutorialEsplora), getString(R.string.tutorialDescrizioneEsplora),R.drawable.esplora_tutorial, getResources().getColor(R.color.Explore), getResources().getColor(R.color.Black), getResources().getColor(R.color.Black));
        addSlide(secondFragment);

        thirdFragment = AppIntro2Fragment.newInstance(getString(R.string.tutorialDiario),getString(R.string.tutorialDescrizioneDiario), R.drawable.diario_tutorial ,getResources().getColor(R.color.Journal), getResources().getColor(R.color.Black), getResources().getColor(R.color.Black) );
        addSlide(thirdFragment);

        fourthFragment = AppIntro2Fragment.newInstance(getString(R.string.tutorialCoupon),getString(R.string.tutorialDescrizioneCoupon), R.drawable.coupon_tutorial ,getResources().getColor(R.color.Coupon), getResources().getColor(R.color.Black), getResources().getColor(R.color.Black));
        addSlide(fourthFragment);

        setDepthAnimation();
        showSkipButton(false);
        setBarColor(Color.TRANSPARENT);


        doneButton.setOnClickListener(DoneListner);
        intent = new Intent(this,MainActivity.class);

    }

    public View.OnClickListener DoneListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    };

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
