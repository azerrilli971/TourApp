package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import uniba.di.sms.ibtourapp.tourapp.dummy.Ristoranti;

import java.util.List;

/**
 * An activity representing a list of Restaurants. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RestaurantDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RestaurantListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private FirebaseAuth mAuth;
    private static int utente = 0;
    View recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsemplice);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mAuth = FirebaseAuth.getInstance();
        UsersDbHelper dbHelper = new UsersDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UsersList.FeedEntry.COLUMN_NAME_TITLE,
                UsersList.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        String selection = UsersList.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { mAuth.getCurrentUser().getUid() };

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
                utente = 1;
            } else {
                utente = 2;
            }
        }
        cursor.close();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(utente == 1){
            fab.setVisibility(View.GONE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RestaurantListActivity.this, CustomListActivity.class);
                String[] testi = {"Ristoranti","Nome Ristorante", "Descrizione Ristorante", "Via Ristorante", "Orario Ristorante"};
                String[] valori;
                i.putExtra("Testi", testi);
                startActivity(i);
            }
        });

        if (findViewById(R.id.restaurant_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.restaurant_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Ristoranti.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final RestaurantListActivity mParentActivity;
        private final List<Ristoranti.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ristoranti.DummyItem item = (Ristoranti.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(RestaurantDetailFragment.ARG_ITEM_ID, item.id);
                    RestaurantDetailFragment fragment = new RestaurantDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.restaurant_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RestaurantDetailActivity.class);
                    intent.putExtra(RestaurantDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(RestaurantListActivity parent,
                                      List<Ristoranti.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restaurant_list_content, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mNomeRistorante.setText(mValues.get(position).nomeRistorante);
            holder.mViaRistorante.setText(mValues.get(position).viaRistorante);
            holder.mOrariRistorante.setText(mValues.get(position).orariRistorante);
            holder.mInfoMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(RestaurantListActivity.this, view);
                    popup.getMenuInflater().inflate(R.menu.menu_info,
                            popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.menuModifica:

                                    Intent i = new Intent(RestaurantListActivity.this, CustomListActivity.class);
                                    String[] testi = {"Ristoranti","Nome Ristorante", "Descrizione Ristorante", "Via Ristorante", "Orari Ristorante"};
                                    String[] valori = {mValues.get(position).nomeRistorante, mValues.get(position).dettagliRistorante, mValues.get(position).viaRistorante, mValues.get(position).orariRistorante, mValues.get(position).id};
                                    i.putExtra("Testi", testi);
                                    i.putExtra("Valori", valori);
                                    startActivity(i);
                                    break;
                                case R.id.menuElimina:
                                    openDialog();
                                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference ref = database.getReference();
                                    ref.child("Ristoranti").child(mValues.get(position).id).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                            mValues.remove(position);
                                            if(position != 0) {
                                                onBindViewHolder(holder, position - 1);
                                            }
                                            Toast.makeText(getApplicationContext(), "Item rimosso correttamente", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                    });
                                    break;

                                default:
                                    break;
                            }

                            return true;
                        }
                    });
                }
            });
            Picasso.get().load(mValues.get(position).immagineRistorante).into(holder.mImmagineRistorante);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
             TextView mNomeRistorante;
             TextView mViaRistorante;
             TextView mOrariRistorante;
             ImageView mImmagineRistorante;
             ImageView mInfoMenu;

            ViewHolder(View view) {
                super(view);
                mNomeRistorante = (TextView) view.findViewById(R.id.ristoranteNome);
                mViaRistorante= (TextView) view.findViewById(R.id.ristoranteVia);
                mOrariRistorante = (TextView) view.findViewById(R.id.ristoranteOrari);
                mImmagineRistorante = (ImageView) view.findViewById(R.id.ristoranteImmagine);
                mInfoMenu = (ImageView) view.findViewById(R.id.iconaMenuInfo);
                if (utente==1){
                    mInfoMenu.setVisibility(View.GONE);
                }

            }
        }
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item){

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.restaurant_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public  void openDialog(){
        DeleteAlertDialog newDialog =  new DeleteAlertDialog();
        newDialog.show(getSupportFragmentManager(), "Delete dialog");
    }
}
