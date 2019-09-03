package uniba.di.sms.ibtourapp.tourapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uniba.di.sms.ibtourapp.tourapp.dummy.Diari;

import static uniba.di.sms.ibtourapp.tourapp.R.id.ricordo;

/**
 * A fragment representing a single Diario detail screen.
 * This fragment is either contained in a {@link DiarioListActivity}
 * in two-pane mode (on tablets) or a {@link DiarioDetailActivity}
 * on handsets.
 */
public class DiarioDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Diari.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DiarioDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Diari.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            appBarLayout.setTitleEnabled(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.diario_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if( Locale.getDefault().getLanguage() != "it" ) {
                mItem.setDescrizioneRicordo(Translate.Translate(mItem.descrizioneRicordo, "it-"+ Locale.getDefault().getLanguage(), getContext()));
            }
            ((TextView) rootView.findViewById(R.id.diario_detail)).setText(mItem.descrizioneRicordo);
            ((TextView) rootView.findViewById(R.id.data)).setText(mItem.dataRicordo);
        }

        return rootView;
    }
}
