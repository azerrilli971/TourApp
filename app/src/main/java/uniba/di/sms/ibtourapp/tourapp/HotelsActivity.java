package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HotelsActivity extends AppCompatActivity {
    String[] nameArray = {"Nicolaus", "Hotel delle Nazioni", "Palace Hotel"};
    String[] infoArray = {"Via Ciasca","Via Sauro","Via Lombardi" };
    String[] priceArray = {"$$$", "$$$$", "$$$"};
    Integer[] imageArray = { R.drawable.nicolaus, R.drawable.hotelnazioni, R.drawable.palace};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        //aggiunta tasto per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListAdapter hotels = new ListAdapter(this, nameArray, infoArray, priceArray, imageArray);
        listView = (ListView) findViewById(R.id.hotelList);
        listView.setAdapter(hotels);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(HotelsActivity.this, DetailHotels.class);
                String message = nameArray[position];
                intent.putExtra("animal", message);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item){

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
