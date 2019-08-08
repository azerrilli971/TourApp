package uniba.di.sms.ibtourapp.tourapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
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
}
