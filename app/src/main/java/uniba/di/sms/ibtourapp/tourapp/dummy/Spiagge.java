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
public class Spiagge {

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
        addItem(new DummyItem("1", "we","aewrwer", "wdkedl", "efklms"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position , String nomeSpiaggia, String viaSpiaggia, String descrizioneSpiaggia, String immagineSpiaggia) {
        return new DummyItem(String.valueOf(position), nomeSpiaggia, viaSpiaggia, descrizioneSpiaggia, immagineSpiaggia);
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
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String id;

        public String getNomeSpiaggia() {
            return nomeSpiaggia;
        }

        public void setNomeSpiaggia(String nomeSpiaggia) {
            this.nomeSpiaggia = nomeSpiaggia;
        }

        public String getViaSpiaggia() {
            return viaSpiaggia;
        }

        public void setViaSpiaggia(String viaSpiaggia) {
            this.viaSpiaggia = viaSpiaggia;
        }

        public String getDescrizioneSpiaggia() {
            return descrizioneSpiaggia;
        }

        public void setDescrizioneSpiaggia(String descrizioneSpiaggia) {
            this.descrizioneSpiaggia = descrizioneSpiaggia;
        }

        public String getImmagineSpiaggia() {
            return immagineSpiaggia;
        }

        public void setImmagineSpiaggia(String immagineSpiaggia) {
            this.immagineSpiaggia = immagineSpiaggia;
        }

        public String nomeSpiaggia;
        public String viaSpiaggia;
        public String descrizioneSpiaggia;
        public String immagineSpiaggia;

        public DummyItem(String id, String nomeSpiaggia, String viaSpiaggia, String descrizioneSpiaggia, String immagineSpiaggia) {
            this.id = id;
            this.nomeSpiaggia = nomeSpiaggia;
            this.viaSpiaggia = viaSpiaggia;
            this.descrizioneSpiaggia = descrizioneSpiaggia;
            this.immagineSpiaggia = immagineSpiaggia;
        }

        public DummyItem(){}
        @Override
        public String toString() {
            return nomeSpiaggia;
        }
    }

    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeSpiaggia(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneSpiaggia(e.getText().toString());
        e = iterator.next();
        item.setViaSpiaggia(e.getText().toString());
        return item;
    }
}
