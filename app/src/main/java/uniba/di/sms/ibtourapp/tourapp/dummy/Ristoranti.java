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
public class Ristoranti {

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
        addItem(new DummyItem("1", "weeeeeeee", "bgweeeeeeee", "ssempre chiuso", "dioejfiojwifo bello se solo fosse aperto", "https://cdn.pixabay.com/photo/2017/03/27/16/50/beach-2179624__340.jpg"));
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String nomeRistorante, String viaRistorante, String orariRistorante, String dettagliRistorante, String immagineRistorante) {
        return new DummyItem(String.valueOf(position), nomeRistorante, viaRistorante, orariRistorante, dettagliRistorante, immagineRistorante);
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
        public String nomeRistorante;
        public String dettagliRistorante;
        public String viaRistorante;
        public String orariRistorante;
        public String immagineRistorante;

        public DummyItem(String id, String nomeRistorante, String viaRistorante, String orariRistorante, String dettagliRistorante, String immagineRistorante) {
            this.id = id;
            this.nomeRistorante = nomeRistorante;
            this.dettagliRistorante = dettagliRistorante;
            this.viaRistorante = viaRistorante;
            this.orariRistorante = orariRistorante;
            this.immagineRistorante = immagineRistorante;
        }
        public DummyItem () {}
        @Override
        public String toString() {
            return nomeRistorante;
        }
    }
}
