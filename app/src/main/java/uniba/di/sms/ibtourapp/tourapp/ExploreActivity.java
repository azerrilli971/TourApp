package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ExploreActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView esploraInteressiCV, esploraDormireCV, esploraMangiareCV;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.homeEsplora);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);
        toolbar.setTitle(R.string.homeEsplora);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //aggiunta tasto per tornare indietro
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        esploraDormireCV = findViewById(R.id.esploraDormire);
        esploraInteressiCV = findViewById(R.id.esploraInteressi);
        esploraMangiareCV = findViewById(R.id.esploraMangiare);

        esploraDormireCV.setOnClickListener(this);
        esploraInteressiCV.setOnClickListener(this);
        esploraMangiareCV.setOnClickListener(this);

    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
