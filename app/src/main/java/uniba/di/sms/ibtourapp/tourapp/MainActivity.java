package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private CardView menuEsploraCV, menuDiarioCV, menuCouponCV;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAsyncTask myAsyncTask = new MyAsyncTask("Diari");
        myAsyncTask.execute();

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.homeEsplora);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        menuEsploraCV = findViewById(R.id.homeEsplora);
        menuDiarioCV = findViewById(R.id.homeDiario);
        menuCouponCV = findViewById(R.id.homeCoupon);

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

            } else {

                menuDiarioCV.setVisibility(View.GONE);
                menuCouponCV.setVisibility(View.GONE);
            }
        }
        cursor.close();



        menuEsploraCV.setOnClickListener(this);
        menuDiarioCV.setOnClickListener(this);
        menuCouponCV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        Intent intent;
        switch(v.getId()){

            case R.id.homeEsplora:{
                MyAsyncTask myAsyncTask = new MyAsyncTask("Gelaterie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Monumenti");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Musei");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Alberghi");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("BeB");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Pizzerie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Ristoranti");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("Spiagge");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("SvagoFamiglie");
                myAsyncTask.execute();
                myAsyncTask = new MyAsyncTask("SvagoGiovani");
                myAsyncTask.execute();
                intent = new Intent(this, ExploreActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.homeDiario:{

                intent = new Intent(this, DiarioListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.homeCoupon:{
                intent = new Intent(this, CouponsActivity.class);
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
