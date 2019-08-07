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

    static {
        // Add some sample items.
            addItem(new DummyItem("1", "Bar Telebari", "Via Fanelli", "09-21", "Una delle piu' antiche gelaterie di Bari"));

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeGelateria, String viaGelateria, String orariGelateria, String descrizioneGelateria) {
        return new DummyItem(String.valueOf(position), nomeGelateria, viaGelateria, orariGelateria, makeDetails(descrizioneGelateria));
    }

    private static String makeDetails(String descrizione) {
        StringBuilder builder = new StringBuilder();

        builder.append("Più informazioni sulla gelateria...\n\n");
        builder.append(descrizione);

        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String nomeGelateria;
        public final String viaGelateria;
        public final String orariGelateria;
        public final String descrizioneGelateria;

        public DummyItem(String id, String nomeGelateria, String viaGelateria, String orariGelateria, String descrizioneGelateria) {
            this.id = id;
            this.nomeGelateria = nomeGelateria;
            this.viaGelateria = viaGelateria;
            this.descrizioneGelateria= makeDetails(descrizioneGelateria);
            this.orariGelateria = orariGelateria;
        }

        @Override
        public String toString() {
            return nomeGelateria;
        }
    }
}
