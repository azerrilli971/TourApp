package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

//modificare Intent per reindirizzare alle activity corrette
public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private CardView mangiareGelaterieCV, mangiarePizzerieCV, mangiareRistorantiCV;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mangiareGelaterieCV = findViewById(R.id.mangiareGelaterie);
        mangiarePizzerieCV = findViewById(R.id.mangiarePizzerie);
        mangiareRistorantiCV = findViewById(R.id.mangiareRistoranti);


        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.esploraMangiare);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
                intent = new Intent(this, PizzeriaListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.mangiareRistoranti:{
                intent = new Intent(this, RestaurantListActivity.class);
                startActivity(intent);
                break;
            }

        }
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){

            case R.id.nav_explore:
                Intent h= new Intent(this,ExploreActivity.class);
                startActivity(h);
                break;
            case R.id.nav_diary:
                Intent i= new Intent(this,JournalActivity.class);
                startActivity(i);
                break;
            case R.id.nav_coupon:
                Intent g= new Intent(this,CouponsActivity.class);
                startActivity(g);
                break;
            case R.id.nav_logout:
                Intent n = new Intent (this, LoginActivity.class);
                startActivity(n);
                break;



        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
