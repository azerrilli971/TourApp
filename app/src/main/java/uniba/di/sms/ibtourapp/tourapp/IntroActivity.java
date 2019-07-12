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
        firstFragment = AppIntro2Fragment.newInstance(getString(R.string.strTitleFirst), getString(R.string.strDescriptionFirst), R.drawable.first_fragment ,Color.DKGRAY );
        addSlide(firstFragment);

        secondFragment = AppIntro2Fragment.newInstance(getString(R.string.titoloHome), "This is a demo of the AppIntro library, with a custom background on each slide!", 0 ,Color.DKGRAY);
        addSlide(secondFragment);

        thirdFragment = AppIntro2Fragment.newInstance(getString(R.string.titoloDiario), "This is a demo of the AppIntro library, with a custom background on each slide!", 0 ,Color.DKGRAY );
        addSlide(thirdFragment);

        fourthFragment = AppIntro2Fragment.newInstance("Welcome4", "This is a demo of the AppIntro library, with a custom background on each slide!", 0 ,Color.DKGRAY);
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
