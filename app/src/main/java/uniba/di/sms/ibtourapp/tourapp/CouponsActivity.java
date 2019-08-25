package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CouponsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView numeroCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.homeCoupon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        numeroCoupon = findViewById(R.id.numeroCoupon);
        //setUpText(int counter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CouponsActivity.this, NewCouponActivity.class);
                startActivity(i);
            }
        });

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
                Intent i= new Intent(this,DiarioListActivity.class);
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
    private void setUpText(int counter) {
        String itemsFound = getResources().getQuantityString(R.plurals.couponsCount, counter, counter);
        numeroCoupon.setText(itemsFound);
    }
}
