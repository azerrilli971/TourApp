package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import uniba.di.sms.ibtourapp.tourapp.dummy.Diari;

import java.util.List;

/**
 * An activity representing a list of Diari. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DiarioDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DiarioListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private boolean mTwoPane;
    private FirebaseAuth mAuth;
    private String image = "https://firebasestorage.googleapis.com/v0/b/tourapp-9d024.appspot.com/o/images%2F947bde07-5138-4029-a77d-34fa9febf8d6?alt=media&token=e2acdc3d-aa98-4414-85d4-7e930df44562";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiarioListActivity.this, CustomListActivity.class);
                String[] testi = {"Diari","Descrizione Ricordo"};
                i.putExtra("Testi", testi);
                startActivity(i);

            }
        });

        if (findViewById(R.id.diario_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        mAuth = FirebaseAuth.getInstance();
        View recyclerView = findViewById(R.id.diario_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Diari.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final DiarioListActivity mParentActivity;
        private final List<Diari.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diari.DummyItem item = (Diari.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(DiarioDetailFragment.ARG_ITEM_ID, item.id);
                    DiarioDetailFragment fragment = new DiarioDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.diario_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DiarioDetailActivity.class);
                    intent.putExtra(DiarioDetailFragment.ARG_ITEM_ID, item.id);
                    intent.putExtra("Immagine", image);
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(DiarioListActivity parent,
                                      List<Diari.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.diario_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mEliminaRicordo.setOnClickListener(new View.OnClickListener() {
                //giustp   @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(DiarioListActivity.this, view);
                    popup.getMenuInflater().inflate(R.menu.menu_elimina,
                            popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            openDialog();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference();
                            ref.child("Diari").child(mAuth.getCurrentUser().getUid()).child(mValues.get(position).id).removeValue(new DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                                    MyAsyncTask task = new MyAsyncTask("Diari");
                                    task.execute();
                                    mValues.remove(position);
                                    if(position != 0) {
                                        onBindViewHolder(holder, position - 1);
                                    }
                                    Toast.makeText(DiarioListActivity.this, "Item rimosso correttamente", Toast.LENGTH_SHORT).show();

                                    notifyDataSetChanged();
                                }
                            });
                        return true;

                        }
                    });
                }
            });



            Picasso.get().load(mValues.get(position).ricordo).into(holder.mRicordo);
            holder.itemView.setTag(mValues.get(position));
            holder.mDataRicordo.setText(mValues.get(position).dataRicordo);
            holder.itemView.setOnClickListener(mOnClickListener);
            image = mValues.get(position).ricordo;
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mRicordo;
            final ImageView mEliminaRicordo;
            final TextView mDataRicordo;

            ViewHolder(View view) {
                super(view);
                mRicordo = (ImageView) view.findViewById(R.id.ricordo);
                mEliminaRicordo = (ImageView) view.findViewById(R.id.tasto_elimina);
                mDataRicordo = (TextView) view.findViewById(R.id.data);
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public  void openDialog(){
        DeleteAlertDialog newDialog =  new DeleteAlertDialog();
        newDialog.show(getSupportFragmentManager(), "Delete dialog");
    }
}
