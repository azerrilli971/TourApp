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
public class Musei {

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
        addItem(new DummyItem("1","uffizi","awe", "bella zio", "oeifmslò", "lfekl"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeMuseo, String viaMuseo, String orariMuseo, String descrizioneMuseo, String immagineMuseo) {
        return new DummyItem(String.valueOf(position), nomeMuseo, viaMuseo, orariMuseo, descrizioneMuseo, immagineMuseo);
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
        public String id;
        public String nomeMuseo;
        public String viaMuseo;
        public String immagineMuseo;
        public String descrizioneMuseo;
        public String orariMuseo;

        public DummyItem(String id, String nomeMuseo, String viaMuseo, String orariMuseo, String descrizioneMuseo, String immagineMuseo) {
            this.id = id;
            this.nomeMuseo = nomeMuseo;
            this.viaMuseo = viaMuseo;
            this.orariMuseo = orariMuseo;
            this.descrizioneMuseo = descrizioneMuseo;
            this.immagineMuseo = immagineMuseo;
        }
        public DummyItem(){}

        @Override
        public String toString() {
            return nomeMuseo;
        }
    }
}
