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
public class Chiese {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static Long COUNT = new Long(0);

    static {
        // Add some sample items.
        addItem(new DummyItem("1","ao", "ce", "emoif", "wfk"));
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeChiesa, String viaChiesa, String descrizioneChiesa, String immagineChiesa) {
        return new DummyItem(String.valueOf(position), nomeChiesa, viaChiesa, descrizioneChiesa, immagineChiesa );
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String id;

        public String getNomeChiesa() {
            return nomeChiesa;
        }

        public void setNomeChiesa(String nomeChiesa) {
            this.nomeChiesa = nomeChiesa;
        }

        public String getViaChiesa() {
            return viaChiesa;
        }

        public void setViaChiesa(String viaChiesa) {
            this.viaChiesa = viaChiesa;
        }

        public String getDescrizioneChiesa() {
            return descrizioneChiesa;
        }

        public void setDescrizioneChiesa(String descrizioneChiesa) {
            this.descrizioneChiesa = descrizioneChiesa;
        }

        public String nomeChiesa;
        public String viaChiesa;
        public String descrizioneChiesa;

        public String getImmagineChiesa() {
            return immagineChiesa;
        }

        public void setImmagineChiesa(String immagineChiesa) {
            this.immagineChiesa = immagineChiesa;
        }

        public String immagineChiesa;

        public DummyItem(String id, String nomeChiesa, String viaChiesa, String descrizioneChiesa, String immagineChiesa) {
            this.id = id;
            this.nomeChiesa = nomeChiesa;
            this.viaChiesa = viaChiesa;
            this.descrizioneChiesa = descrizioneChiesa;
            this.immagineChiesa= immagineChiesa;
        }

        public DummyItem(){}

        @Override
        public String toString() {
            return nomeChiesa;
        }
    }

    public static DummyItem addItemList(ArrayList<EditText> lista){
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeChiesa(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneChiesa(e.getText().toString());
        e = iterator.next();
        item.setViaChiesa(e.getText().toString());
        return item;
    }
}
