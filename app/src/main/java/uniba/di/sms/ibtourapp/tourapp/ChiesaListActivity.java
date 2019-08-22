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

import uniba.di.sms.ibtourapp.tourapp.dummy.Chiese;

import java.util.List;

/**
 * An activity representing a list of Chiese. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ChiesaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ChiesaListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiesa_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsemplice);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
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
                Toast.makeText(getApplicationContext(), "Questo utente non è un infopoint", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Questo utente è un infopoint", Toast.LENGTH_LONG).show();
            }
        }
        cursor.close();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChiesaListActivity.this, CustomListActivity.class);
                String[] testi = {"Chiese","Nome Chiesa", "Descrizione Chiesa", "Via Chiesa"};
                String[] valori;
                i.putExtra("Testi", testi);
                startActivity(i);
            }
        });

        if (findViewById(R.id.chiesa_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.chiesa_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Chiese.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ChiesaListActivity mParentActivity;
        private final List<Chiese.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chiese.DummyItem item = (Chiese.DummyItem) view.getTag();
                if(view.getId()== R.id.iconaMenuInfo){
                    //Toast.makeText(getApplicationContext(), "Funziona", Toast.LENGTH_LONG).show();
                }
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ChiesaDetailFragment.ARG_ITEM_ID, item.id);
                    ChiesaDetailFragment fragment = new ChiesaDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.chiesa_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ChiesaDetailActivity.class);
                    intent.putExtra(ChiesaDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ChiesaListActivity parent,
                                      List<Chiese.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chiesa_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mNomeChiesa.setText(mValues.get(position).nomeChiesa);
            holder.mViaChiesa.setText(mValues.get(position).viaChiesa);
            Picasso.get().load(mValues.get(position).immagineChiesa).into(holder.mImmagineChiesa);
            holder.mInfoMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(ChiesaListActivity.this, view);
                    popup.getMenuInflater().inflate(R.menu.menu_info,
                            popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.menuModifica:

                                    //Or Some other code you want to put here.. This is just an example
                                    Toast.makeText(getApplicationContext(), " Install Clicked at position " + " : " , Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(ChiesaListActivity.this, CustomListActivity.class);
                                    String[] testi = {"Chiese","Nome Chiesa", "Descrizione Chiesa", "Via Chiesa"};
                                    String[] valori = {mValues.get(position).nomeChiesa, mValues.get(position).descrizioneChiesa, mValues.get(position).viaChiesa, mValues.get(position).id};
                                    i.putExtra("Testi", testi);
                                    i.putExtra("Valori", valori);
                                    startActivity(i);
                                    break;
                                case R.id.menuElimina:

                                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference ref = database.getReference();
                                    ref.child("Chiese").child(mValues.get(position).id).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                            MyAsyncTask task = new MyAsyncTask("Chiese");
                                            task.execute();
                                            mValues.remove(position);
                                            if(position != 0) {
                                                onBindViewHolder(holder, position - 1);
                                            }
                                            Toast.makeText(getApplicationContext(), "Item rimosso correttamente", Toast.LENGTH_SHORT).show();
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
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mNomeChiesa;
            TextView mViaChiesa;
            ImageView mImmagineChiesa;
            ImageView mInfoMenu;


            ViewHolder(View view) {
                super(view);
                mNomeChiesa = (TextView) view.findViewById(R.id.chiesaNome);
                mViaChiesa = (TextView) view.findViewById(R.id.chiesaVia);
                mImmagineChiesa = (ImageView) view.findViewById(R.id.chiesaImmagine);
                mInfoMenu = (ImageView) view.findViewById(R.id.iconaMenuInfo);
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

}
