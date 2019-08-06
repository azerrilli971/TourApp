package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView menuEsploraCV, menuDiarioCV, menuCouponCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuEsploraCV = findViewById(R.id.homeEsplora);
        menuDiarioCV = findViewById(R.id.homeDiario);
        menuCouponCV = findViewById(R.id.homeCoupon);

        menuEsploraCV.setOnClickListener(this);
        menuDiarioCV.setOnClickListener(this);
        menuCouponCV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        Intent intent;
        switch(v.getId()){

            case R.id.homeEsplora:{
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                intent = new Intent(this, ExploreActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.homeDiario:{
                intent = new Intent(this, JournalActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.homeCoupon:{
                intent = new Intent(this, CouponsActivity.class);
                startActivity(intent);
                break;
            }

        }
    }

}
