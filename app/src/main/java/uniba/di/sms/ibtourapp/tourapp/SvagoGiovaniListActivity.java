package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;
import android.content.Intent;
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

import com.squareup.picasso.Picasso;

import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoGiovani;

import java.util.List;

/**
 * An activity representing a list of SvaghiGiovani. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SvagoGiovaniDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SvagoGiovaniListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svagogiovani_list);

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

        if (findViewById(R.id.svagogiovani_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.svagogiovani_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, SvagoGiovani.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final SvagoGiovaniListActivity mParentActivity;
        private final List<SvagoGiovani.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvagoGiovani.DummyItem item = (SvagoGiovani.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(SvagoGiovaniDetailFragment.ARG_ITEM_ID, item.id);
                    SvagoGiovaniDetailFragment fragment = new SvagoGiovaniDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.svagogiovani_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SvagoGiovaniDetailActivity.class);
                    intent.putExtra(SvagoGiovaniDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(SvagoGiovaniListActivity parent,
                                      List<SvagoGiovani.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.svagogiovani_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mNomeSvagoG.setText(mValues.get(position).nomeSvagoG);
            holder.mViaSvagoG.setText(mValues.get(position).viaSvagoG);
            holder.mOrariSvagoG.setText(mValues.get(position).orariSvagoG);
            holder.mCostoSvagoG.setText(mValues.get(position).costoSvagoG);
            Picasso.get().load(mValues.get(position).immagineSvagoG).into(holder.mImmagineSvagoG);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mNomeSvagoG;
            TextView mViaSvagoG;
            TextView mOrariSvagoG;
            TextView mCostoSvagoG;
            ImageView mImmagineSvagoG;


            ViewHolder(View view) {
                super(view);
                mNomeSvagoG = (TextView) view.findViewById(R.id.svagoGNome);
                mViaSvagoG = (TextView) view.findViewById(R.id.svagoGVia);
                mOrariSvagoG = (TextView) view.findViewById(R.id.svagoGOrari);
                mCostoSvagoG = (TextView) view.findViewById(R.id.svagoGCosto);
                mImmagineSvagoG = (ImageView) view.findViewById(R.id.svagoGImmagine);

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