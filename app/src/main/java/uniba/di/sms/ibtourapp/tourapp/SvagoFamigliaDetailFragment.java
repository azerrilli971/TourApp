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

import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoFamiglie;

/**
 * A fragment representing a single SvagoFamiglia detail screen.
 * This fragment is either contained in a {@link SvagoFamigliaListActivity}
 * in two-pane mode (on tablets) or a {@link SvagoFamigliaDetailActivity}
 * on handsets.
 */
public class SvagoFamigliaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private SvagoFamiglie.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SvagoFamigliaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = SvagoFamiglie.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.nomeSvagoF);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.svagofamiglia_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if( Locale.getDefault().getLanguage() != "it" ) {
                mItem.setDescrizioneSvagoF(Translate.Translate(mItem.descrizioneSvagoF, "it-"+ Locale.getDefault().getLanguage(), getContext()));
            }
            ((TextView) rootView.findViewById(R.id.svagofamiglia_detail)).setText(mItem.descrizioneSvagoF);
        }

        return rootView;
    }
}
