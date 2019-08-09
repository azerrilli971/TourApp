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
public class SvagoGiovani {

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
        addItem(new DummyItem("1", "werds", "dklas", "oidskl", "fejdsklm", "dskjlm", "gfrdkjlc"));
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position,String nomeSvagoG, String viaSvagoG, String orariSvagoG, String costoSvagoG, String descrizioneSvagoG, String immagineSvagoG) {
        return new DummyItem(String.valueOf(position), nomeSvagoG, viaSvagoG, orariSvagoG, costoSvagoG, descrizioneSvagoG, immagineSvagoG);
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
        public String nomeSvagoG;
        public String viaSvagoG;
        public String orariSvagoG;
        public String costoSvagoG;
        public String descrizioneSvagoG;
        public String immagineSvagoG;

        public DummyItem(String id, String nomeSvagoG, String viaSvagoG, String orariSvagoG, String costoSvagoG, String descrizioneSvagoG, String immagineSvagoG) {
            this.id = id;
            this.nomeSvagoG = nomeSvagoG;
            this.viaSvagoG = viaSvagoG;
            this.orariSvagoG = orariSvagoG;
            this.costoSvagoG = costoSvagoG;
            this.descrizioneSvagoG = descrizioneSvagoG;
            this.immagineSvagoG = immagineSvagoG;
        }

        public DummyItem(){}


        @Override
        public String toString() {
            return nomeSvagoG ;
        }
    }
}
