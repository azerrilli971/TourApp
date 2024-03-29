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

import uniba.di.sms.ibtourapp.tourapp.dummy.Musei;

/**
 * A fragment representing a single Museo detail screen.
 * This fragment is either contained in a {@link MuseoListActivity}
 * in two-pane mode (on tablets) or a {@link MuseoDetailActivity}
 * on handsets.
 */
public class MuseoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Musei.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MuseoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Musei.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.nomeMuseo);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.museo_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if( Locale.getDefault().getLanguage() != "it" ) {
                mItem.setDescrizioneMuseo(Translate.Translate(mItem.descrizioneMuseo, "it-"+ Locale.getDefault().getLanguage(), getContext()));
            }
            ((TextView) rootView.findViewById(R.id.museo_detail)).setText(mItem.descrizioneMuseo);
        }

        return rootView;
    }
}
