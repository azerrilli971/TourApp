package uniba.di.sms.ibtourapp.tourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uniba.di.sms.ibtourapp.tourapp.dummy.Coupon;

public class CouponsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView numeroCoupon;
    private Button usaCoupon;
    private static int counter = 0;
    FirebaseAuth auth ;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference();
    private String codice;
    private String key = "00";
    ArrayList<Coupon> lista = new ArrayList<Coupon>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        auth = FirebaseAuth.getInstance();

        usaCoupon = findViewById(R.id.buttonUsaCoupon);

        reference.child("Coupon").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot data: dataSnapshot.getChildren()
                ) {
                    lista.add(data.getValue(Coupon.class));
                    lista.get(i++).setKey(data.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        usaCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codice = lista.get(0).getId();
                if(lista.get(0).getKey() != null) {
                    key = lista.get(0).getKey();
                    reference.child("Coupon").child(auth.getCurrentUser().getUid()).child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            lista.remove(0);
                            //Toast.makeText(getApplicationContext(), "skdo", Toast.LENGTH_SHORT).show();
                        }
                    });
                    counter --;
                    setUpText(counter);
                }
                openDialog();
            }
        });

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //setting toolbar as an actionbar
        toolbar.setTitle(R.string.homeCoupon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburger);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        numeroCoupon = findViewById(R.id.numeroCoupon);
        reference.child("Coupon").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren() == true) {
                    counter = (int) dataSnapshot.getChildrenCount();
                    setUpText(counter);
                } else {
                    counter = 0;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setUpText(counter);
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
    protected void onRestart() {
        super.onRestart();
        reference.child("Coupon").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren() == true) {
                    counter = (int) dataSnapshot.getChildrenCount();
                    setUpText(counter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setUpText(counter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reference.child("Coupon").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren() == true) {
                    counter = (int) dataSnapshot.getChildrenCount();
                    setUpText(counter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
    public  void openDialog(){
        UsingCouponAlert newDialog =  new UsingCouponAlert();
        newDialog.codice = codice;
        newDialog.show(getSupportFragmentManager(), "Coupon dialog");
    }
}
