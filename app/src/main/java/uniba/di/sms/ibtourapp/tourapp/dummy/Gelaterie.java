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
public class Gelaterie {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeGelateria, String viaGelateria, String orariGelateria, String descrizioneGelateria, String immagine) {
        return new DummyItem(String.valueOf(position), nomeGelateria, viaGelateria, orariGelateria, descrizioneGelateria, immagine);
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public  String id;
        public  String nomeGelateria;
        public  String viaGelateria;
        public  String orariGelateria;

        public String getNomeGelateria() {
            return nomeGelateria;
        }

        public void setNomeGelateria(String nomeGelateria) {
            this.nomeGelateria = nomeGelateria;
        }

        public String getViaGelateria() {
            return viaGelateria;
        }

        public void setViaGelateria(String viaGelateria) {
            this.viaGelateria = viaGelateria;
        }

        public String getOrariGelateria() {
            return orariGelateria;
        }

        public void setOrariGelateria(String orariGelateria) {
            this.orariGelateria = orariGelateria;
        }

        public String getDescrizioneGelateria() {
            return descrizioneGelateria;
        }

        public void setDescrizioneGelateria(String descrizioneGelateria) {
            this.descrizioneGelateria = descrizioneGelateria;
        }

        public String getImmagine() {
            return immagine;
        }

        public void setImmagine(String immagine) {
            this.immagine = immagine;
        }

        public  String descrizioneGelateria;
        public  String immagine;

        public DummyItem(String id, String nomeGelateria, String viaGelateria, String orariGelateria, String descrizioneGelateria, String immagine) {
            this.id = id;
            this.nomeGelateria = nomeGelateria;
            this.viaGelateria = viaGelateria;
            this.descrizioneGelateria=descrizioneGelateria;
            this.orariGelateria = orariGelateria;
            this.immagine = immagine;
        }

        public DummyItem() {}

        @Override
        public String toString() {
            return nomeGelateria;
        }
    }
    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeGelateria(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneGelateria(e.getText().toString());
        e = iterator.next();
        item.setViaGelateria(e.getText().toString());
        e = iterator.next();
        item.setOrariGelateria(e.getText().toString());
        return item;
    }
}
