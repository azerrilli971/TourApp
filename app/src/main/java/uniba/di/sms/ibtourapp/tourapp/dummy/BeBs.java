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
public class BeBs {

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
        addItem(new DummyItem("1", "Nicolaus", "via we", "molto carino", "iodmsklÃ²", "nskla"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position ,String nomeBB, String viaBB, String costoBB, String descrizioneBB, String immagineBB) {
        return new DummyItem(String.valueOf(position), nomeBB, viaBB, costoBB, descrizioneBB, immagineBB);
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
        public  String id;
        public String nomeBB;
        public String viaBB;
        public String costoBB;
        public String descrizioneBB;
        public String immagineBB;

        public DummyItem(String id, String nomeBB, String viaBB, String costoBB, String descrizioneBB, String immagineBB) {
            this.id = id;
            this.nomeBB = nomeBB;
            this.viaBB = viaBB;
            this.costoBB=costoBB;
            this.descrizioneBB=descrizioneBB;
            this.immagineBB=immagineBB;
        }

        @Override
        public String toString() {
            return nomeBB;
        }
    }
}
