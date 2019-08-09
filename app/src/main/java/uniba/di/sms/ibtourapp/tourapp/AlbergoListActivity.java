package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import uniba.di.sms.ibtourapp.tourapp.dummy.Alberghi;

import java.util.List;

/**
 * An activity representing a list of Alberghi. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link AlbergoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class AlbergoListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albergo_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsemplice);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.albergo_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.albergo_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Alberghi.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final AlbergoListActivity mParentActivity;
        private final List<Alberghi.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alberghi.DummyItem item = (Alberghi.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AlbergoDetailFragment.ARG_ITEM_ID, item.id);
                    AlbergoDetailFragment fragment = new AlbergoDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.albergo_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AlbergoDetailActivity.class);
                    intent.putExtra(AlbergoDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(AlbergoListActivity parent,
                                      List<Alberghi.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.albergo_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mNomeHotel.setText(mValues.get(position).nomeHotel);
            holder.mViaHotel.setText(mValues.get(position).viaHotel);
            holder.mCostoHotel.setText(mValues.get(position).costoHotel);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mNomeHotel;
            TextView mViaHotel;
            TextView mCostoHotel;
            ImageView mImmagineHOtel;

            ViewHolder(View view) {
                super(view);
                mNomeHotel = (TextView) view.findViewById(R.id.hotelNome);
                mViaHotel = (TextView) view.findViewById(R.id.hotelVia);
                mCostoHotel = (TextView) view.findViewById(R.id.hotelCosto);
                mImmagineHOtel = (ImageView) view.findViewById(R.id.hotelImmagine);
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
