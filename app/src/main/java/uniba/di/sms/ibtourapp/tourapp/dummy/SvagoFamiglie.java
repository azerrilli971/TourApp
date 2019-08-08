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
public class SvagoFamiglie {

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

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeSvagoF, String viaSvagoF, String orariSvagoF, String costoSvagoF, String descrizioneSvagoF, String immagineSvagoF) {
        return new DummyItem(String.valueOf(position), nomeSvagoF, viaSvagoF, orariSvagoF, costoSvagoF, descrizioneSvagoF, immagineSvagoF );
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
        public String nomeSvagoF;
        public String viaSvagoF;
        public String orariSvagoF;
        public String costoSvagoF;
        public String descrizioneSvagoF;
        public String immagineSvagoF;

        public DummyItem(String id, String nomeSvagoF, String viaSvagoF, String orariSvagoF, String costoSvagoF, String descrizioneSvagoF, String immagineSvagoF) {
            this.id = id;
            this.nomeSvagoF = nomeSvagoF;
            this.viaSvagoF = viaSvagoF;
            this.orariSvagoF = orariSvagoF;
            this.costoSvagoF = costoSvagoF;
            this.descrizioneSvagoF = descrizioneSvagoF;
            this.immagineSvagoF = immagineSvagoF;
        }

        public  DummyItem(){}
        @Override
        public String toString() {
            return nomeSvagoF;
        }
    }
}
