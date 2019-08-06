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
public class DummyContent {

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

    private static DummyItem createDummyItem(int id, String nomeMonumento, String viaMonumento, String descrizioneMonumento) {
        return new DummyItem(String.valueOf(id), nomeMonumento, viaMonumento, makeDetails(descrizioneMonumento));
    }

    private static String makeDetails(String descrizione) {
        StringBuilder builder = new StringBuilder();
        builder.append("Per saperne di più...\n ");
        builder.append(descrizione);

        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String nomeMonumento;
        public String viaMonumento;
        public String descrizioneMonumento;



        public DummyItem(String id, String nomeMonumento, String viaMonumento, String descrizioneMonumento) {
            this.id = id;
            this.nomeMonumento = nomeMonumento;
            this.viaMonumento = viaMonumento;
            this.descrizioneMonumento = descrizioneMonumento;
        }

        public DummyItem() {}

        @Override
        public String toString() {
            return nomeMonumento;
        }
    }
}
