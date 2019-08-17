package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
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
import com.squareup.picasso.Picasso;

import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoFamiglie;

import java.util.List;

/**
 * An activity representing a list of SvaghiFamiglia. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SvagoFamigliaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SvagoFamigliaListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svagofamiglia_list);

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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(SvagoFamigliaListActivity.this, MainActivity.class));
            }
        });

        if (findViewById(R.id.svagofamiglia_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.svagofamiglia_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, SvagoFamiglie.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final SvagoFamigliaListActivity mParentActivity;
        private final List<SvagoFamiglie.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvagoFamiglie.DummyItem item = (SvagoFamiglie.DummyItem) view.getTag();
                if(view.getId()== R.id.iconaMenuInfo){
                    //Toast.makeText(getApplicationContext(), "Funziona", Toast.LENGTH_LONG).show();
                }
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(SvagoFamigliaDetailFragment.ARG_ITEM_ID, item.id);
                    SvagoFamigliaDetailFragment fragment = new SvagoFamigliaDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.svagofamiglia_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SvagoFamigliaDetailActivity.class);
                    intent.putExtra(SvagoFamigliaDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(SvagoFamigliaListActivity parent,
                                      List<SvagoFamiglie.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.svagofamiglia_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mNomeSvago.setText(mValues.get(position).nomeSvagoF);
            holder.mViaSvago.setText(mValues.get(position).viaSvagoF);
            holder.mOrariSvago.setText(mValues.get(position).orariSvagoF);
            holder.mCostoSvago.setText(mValues.get(position).costoSvagoF);
            Picasso.get().load(mValues.get(position).immagineSvagoF).into(holder.mImmagineSvago);
            holder.mInfoMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(SvagoFamigliaListActivity.this, view);
                    popup.getMenuInflater().inflate(R.menu.menu_info,
                            popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.menuModifica:

                                    //Or Some other code you want to put here.. This is just an example. e puzzi
                                    Toast.makeText(getApplicationContext(), " Install Clicked at position " + " : " , Toast.LENGTH_LONG).show();

                                    break;
                                case R.id.menuElimina:

                                    Toast.makeText(getApplicationContext(), "Add to Wish List Clicked at position " + " : " , Toast.LENGTH_LONG).show();

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
            TextView mNomeSvago;
            TextView mViaSvago;
            TextView mOrariSvago;
            TextView mCostoSvago;
            ImageView mImmagineSvago;
            ImageView mInfoMenu;


            ViewHolder(View view) {
                super(view);
                mNomeSvago = (TextView) view.findViewById(R.id.svagoNome);
                mViaSvago = (TextView) view.findViewById(R.id.svagoVia);
                mOrariSvago = (TextView) view.findViewById(R.id.svagoOrari);
                mCostoSvago = (TextView) view.findViewById(R.id.svagoCosto);
                mImmagineSvago = (ImageView) view.findViewById(R.id.svagoImmagine);
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
