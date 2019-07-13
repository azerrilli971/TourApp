package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class InterestActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView interessiFamiglieCv, interessiGiovaniCV, interessiLidiCV, interessiMuseiCV, interessiMonumentiCV, interessiChieseCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        //aggiunta tasto per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        interessiChieseCV = findViewById(R.id.interessiChiese);
        interessiFamiglieCv = findViewById(R.id.interessiFamiglie);
        interessiGiovaniCV = findViewById(R.id.interessiGiovani);
        interessiLidiCV = findViewById(R.id.interessiLidi);
        interessiMonumentiCV = findViewById(R.id.interessiMonumenti);
        interessiMuseiCV = findViewById(R.id.interessiMusei);

        interessiMonumentiCV.setOnClickListener(this);
        interessiLidiCV.setOnClickListener(this);
        interessiGiovaniCV.setOnClickListener(this);
        interessiFamiglieCv.setOnClickListener(this);
        interessiChieseCV.setOnClickListener(this);
        interessiMuseiCV.setOnClickListener(this);

    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item){

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){

        Intent intent;
        switch(v.getId()){

            case R.id.interessiChiese:{
                intent = new Intent(this, ChurchActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiFamiglie:{
                intent = new Intent(this, FamilyActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiGiovani:{
                intent = new Intent(this, YoungActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.interessiLidi:{
                intent = new Intent(this, BeachActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiMonumenti:{
                intent = new Intent(this, MonumentActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiMusei:{
                intent = new Intent(this, MuseumActivity.class);
                startActivity(intent);
                break;
            }

        }
    }
}
