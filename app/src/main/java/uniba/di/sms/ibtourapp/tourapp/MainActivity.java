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
                MyAsyncTask myAsyncTask = new MyAsyncTask("Gelaterie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Monumenti");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Musei");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Alberghi");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("BeB");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Pizzerie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Ristoranti");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Spiagge");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("SvagoFamiglie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("SvagoGiovani");
                myAsyncTask.execute();
                intent = new Intent(this, ExploreActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.homeDiario:{
                intent = new Intent(this, DiarioListActivity.class);
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
