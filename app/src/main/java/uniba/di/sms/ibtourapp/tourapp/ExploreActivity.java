package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class ExploreActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView esploraInteressiCV, esploraDormireCV, esploraMangiareCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        //aggiunta tasto per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        esploraDormireCV = findViewById(R.id.esploraDormire);
        esploraInteressiCV = findViewById(R.id.esploraInteressi);
        esploraMangiareCV = findViewById(R.id.esploraMangiare);

        esploraDormireCV.setOnClickListener(this);
        esploraInteressiCV.setOnClickListener(this);
        esploraMangiareCV.setOnClickListener(this);

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

            case R.id.esploraDormire:{
                intent = new Intent(this, HotelActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.esploraInteressi:{
                intent = new Intent(this, InterestActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.esploraMangiare:{
                intent = new Intent(this, RestaurantActivity.class);
                startActivity(intent);
                break;
            }

        }
    }
}
