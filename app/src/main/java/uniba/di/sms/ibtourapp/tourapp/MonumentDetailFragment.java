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
import java.util.concurrent.ExecutionException;

import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;

/**
 * A fragment representing a single Monument detail screen.
 * This fragment is either contained in a {@link MonumentListActivity}
 * in two-pane mode (on tablets) or a {@link MonumentDetailActivity}
 * on handsets.
 */
public class MonumentDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Monumenti.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MonumentDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Monumenti.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.nomeMonumento);
            }
        }
    }
    String Translate(String textToBeTranslated,String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(getContext());
        String translationResult = null;
        try {
            translationResult = translatorBackgroundTask.execute(textToBeTranslated,languagePair).get(); // Returns the translated text as a String
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return translationResult;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.monument_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if( Locale.getDefault().getLanguage() != "it" ) {
                mItem.setDescrizioneMonumento(Translate(mItem.descrizioneMonumento, "it-"+ Locale.getDefault().getLanguage()));
            }
            ((TextView) rootView.findViewById(R.id.monument_detail)).setText(mItem.descrizioneMonumento);
        }

        return rootView;
    }
}
