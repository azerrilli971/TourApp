package uniba.di.sms.ibtourapp.tourapp.dummy;

import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Diari {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String ricordo, String descrizioneRicordo) {
        return new DummyItem(String.valueOf(position), "Item " + position, descrizioneRicordo);
    }

    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setDescrizioneRicordo(e.getText().toString());
        return item;
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String ricordo;
        public String descrizioneRicordo;
        public String dataRicordo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRicordo() {
            return ricordo;
        }

        public void setRicordo(String ricordo) {
            this.ricordo = ricordo;
        }

        public String getDescrizioneRicordo() {
            return descrizioneRicordo;
        }

        public void setDescrizioneRicordo(String descrizioneRicordo) {
            this.descrizioneRicordo = descrizioneRicordo;
        }

        public String getDataRicordo() {
            return dataRicordo;
        }

        public void setDataRicordo(String dataRicordo) {
            this.dataRicordo = dataRicordo;
        }

        public DummyItem(String id, String ricordo, String descrizioneRicordo) {
            this.id = id;
            this.ricordo = ricordo;
            this.descrizioneRicordo = descrizioneRicordo;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy ");
            String datetime = dateformat.format(c.getTime());
            this.dataRicordo = datetime; //java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        }

        public DummyItem(){}

        @Override
        public String toString() {
            return descrizioneRicordo;
        }
    }
}
