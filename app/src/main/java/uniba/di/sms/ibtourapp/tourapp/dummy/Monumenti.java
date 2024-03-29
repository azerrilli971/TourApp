package uniba.di.sms.ibtourapp.tourapp.dummy;

import android.widget.EditText;

import java.util.ArrayList;
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
public class Monumenti {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static Long COUNT = new Long(0);

    public static void addItem(DummyItem item) {
        if(!ITEMS.contains(item)) {
            ITEMS.add(item);
            ITEM_MAP.put(item.id, item);
        }
    }

    private static String makeDetails(String descrizione) {
        StringBuilder builder = new StringBuilder();
        builder.append(descrizione);

        return builder.toString();
    }

    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeMonumento(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneMonumento(e.getText().toString());
        e = iterator.next();
        item.setViaMonumento(e.getText().toString());
        return item;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {

        public String getNomeMonumento() {
            return nomeMonumento;
        }

        public void setNomeMonumento(String nomeMonumento) {
            this.nomeMonumento = nomeMonumento;
        }

        public String getViaMonumento() {
            return viaMonumento;
        }

        public void setViaMonumento(String viaMonumento) {
            this.viaMonumento = viaMonumento;
        }

        public String getDescrizioneMonumento() {
            return descrizioneMonumento;
        }

        public void setDescrizioneMonumento(String descrizioneMonumento) {
            this.descrizioneMonumento = descrizioneMonumento;
        }


        public String getImmagineMonumento() {
            return immagineMonumento;
        }

        public void setImmagineMonumento(String immagineMonumento) {
            this.immagineMonumento = immagineMonumento;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String id;
        public String nomeMonumento;
        public String viaMonumento;
        public String descrizioneMonumento;
        public String immagineMonumento;



        public DummyItem(String id, String nomeMonumento, String viaMonumento, String descrizioneMonumento, String immagineMonumento) {
            this.id = id;
            this.nomeMonumento = nomeMonumento;
            this.viaMonumento = viaMonumento;
            this.descrizioneMonumento = descrizioneMonumento;
            this.immagineMonumento = immagineMonumento;
        }

        public DummyItem() {}

        @Override
        public String toString() {
            return nomeMonumento;
        }
    }
}
