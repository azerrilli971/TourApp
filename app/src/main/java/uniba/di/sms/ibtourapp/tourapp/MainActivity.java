package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView menuEsploraCV, menuDiarioCV, menuCouponCV;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                MyAsyncTask myAsyncTask = new MyAsyncTask("Diari");
                myAsyncTask.execute();
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

}
