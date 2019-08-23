package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//aggiustare activity dell'intent
public class HotelActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    private CardView dormireBBCV, dormireHotelCV;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        dormireBBCV = findViewById(R.id.dormireBB);
        dormireHotelCV= findViewById(R.id.dormireHotel);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.esploraDormire);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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
        boolean utente = true;

        mAuth = FirebaseAuth.getInstance();
        UsersDbHelper dbHelper = new UsersDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UsersList.FeedEntry.COLUMN_NAME_TITLE,
                UsersList.FeedEntry.COLUMN_NAME_SUBTITLE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UsersList.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { mAuth.getCurrentUser().getUid() };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UsersList.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                UsersList.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(UsersList.FeedEntry.COLUMN_NAME_SUBTITLE));
            if(itemId == 0) {

                utente = true;
                Toast.makeText(getApplicationContext(), "Questo utente non è un infopoint", Toast.LENGTH_LONG).show();
            } else {

                utente = false;
            }
        }
        cursor.close();
        switch (id){

            case R.id.nav_explore:
                Intent h= new Intent(this,ExploreActivity.class);
                startActivity(h);
                break;
            case R.id.nav_diary:
                if (utente) {
                    Intent i = new Intent(this, DiarioListActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Questa funzione non è disponibile per gli InfoPoint", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.nav_coupon:
                if (utente){
                    Intent g= new Intent(this,CouponsActivity.class);
                    startActivity(g);}
                else {
                    Toast.makeText(getApplicationContext(), "Questa funzione non è disponibile per gli InfoPoint", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent n = new Intent (this, LoginActivity.class);
                startActivity(n);
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
