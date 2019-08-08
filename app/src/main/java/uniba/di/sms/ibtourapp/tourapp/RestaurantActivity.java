package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

//modificare Intent per reindirizzare alle activity corrette
public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mangiareGelaterieCV, mangiarePizzerieCV, mangiareRistorantiCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        //aggiunta tasto per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mangiareGelaterieCV = findViewById(R.id.mangiareGelaterie);
        mangiarePizzerieCV = findViewById(R.id.mangiarePizzerie);
        mangiareRistorantiCV = findViewById(R.id.mangiareRistoranti);

        mangiareGelaterieCV .setOnClickListener(this);
        mangiarePizzerieCV.setOnClickListener(this);
        mangiareRistorantiCV.setOnClickListener(this);

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

            case R.id.mangiareGelaterie:{
                intent = new Intent(this, GelateriaListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.mangiarePizzerie:{
                intent = new Intent(this, PizzerieActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.mangiareRistoranti:{
                intent = new Intent(this, RestaurantsActivity.class);
                startActivity(intent);
                break;
            }

        }
    }
}
