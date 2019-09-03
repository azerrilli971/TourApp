package uniba.di.sms.ibtourapp.tourapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import uniba.di.sms.ibtourapp.tourapp.dummy.Spiagge;

/**
 * A fragment representing a single Spiaggia detail screen.
 * This fragment is either contained in a {@link SpiaggiaListActivity}
 * in two-pane mode (on tablets) or a {@link SpiaggiaDetailActivity}
 * on handsets.
 */
public class SpiaggiaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Spiagge.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpiaggiaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Spiagge.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.nomeSpiaggia);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spiaggia_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if( Locale.getDefault().getLanguage() != "it" ) {
                mItem.setDescrizioneSpiaggia(Translate.Translate(mItem.descrizioneSpiaggia, "it-"+ Locale.getDefault().getLanguage(), getContext()));
            }
            ((TextView) rootView.findViewById(R.id.spiaggia_detail)).setText(mItem.descrizioneSpiaggia);
        }

        return rootView;
    }
}
