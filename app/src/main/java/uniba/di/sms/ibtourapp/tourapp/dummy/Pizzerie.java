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
public class Pizzerie {

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
        addItem(new DummyItem("1", "la Lira", "via Giulio pastore", "woe", "jskfd", "oefjs"));

    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    //costruttore vuoto
    public Pizzerie(){}

    private static DummyItem createDummyItem(int position, String nomePizzeria, String viaPizzeria, String orariPizzeria, String dettagliPizzeria, String immaginePizzeria ) {
        return new DummyItem(String.valueOf(position), nomePizzeria,viaPizzeria, orariPizzeria, dettagliPizzeria, immaginePizzeria);
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
        public String nomePizzeria;
        public String viaPizzeria;
        public String orariPizzeria;
        public String immaginePizzeria;
        public String dettagliPizzeria;

        public DummyItem(String id, String nomePizzeria, String viaPizzeria, String orariPizzeria, String dettagliPizzeria, String immaginePizzeria) {
            this.id = id;
            this.nomePizzeria = nomePizzeria;
            this.viaPizzeria = viaPizzeria;
            this.orariPizzeria = orariPizzeria;
            this.immaginePizzeria  = immaginePizzeria;
            this.dettagliPizzeria = dettagliPizzeria;
        }
        public DummyItem() {}
        @Override
        public String toString() {
            return nomePizzeria;
        }
    }
}
