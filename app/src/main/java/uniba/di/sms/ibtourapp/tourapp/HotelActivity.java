package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

//aggiustare activity dell'intent
public class HotelActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView dormireBBCV, dormireHotelCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        //aggiunta tasto per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dormireBBCV = findViewById(R.id.dormireBB);
        dormireHotelCV= findViewById(R.id.dormireHotel);

        dormireBBCV.setOnClickListener(this);
        dormireHotelCV.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item){

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.dormireBB: {
                intent = new Intent(this, BeBListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.dormireHotel: {
                intent = new Intent(this, AlbergoListActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
