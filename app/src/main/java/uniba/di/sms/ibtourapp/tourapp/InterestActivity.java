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

public class InterestActivity extends AppCompatActivity implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener{
    private CardView interessiFamiglieCv, interessiGiovaniCV, interessiLidiCV, interessiMuseiCV, interessiMonumentiCV, interessiChieseCV;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);


        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.esploraInteressi);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
                intent = new Intent(this, ChiesaListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiFamiglie:{
                intent = new Intent(this, SvagoFamigliaListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiGiovani:{
                intent = new Intent(this, SvagoGiovaniListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.interessiLidi:{
                intent = new Intent(this, SpiaggiaListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiMonumenti:{
                intent = new Intent(this, MonumentListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.interessiMusei:{
                intent = new Intent(this, MuseoListActivity.class);
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
