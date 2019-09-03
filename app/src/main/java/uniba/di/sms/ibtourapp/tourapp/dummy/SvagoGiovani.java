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
public class SvagoGiovani {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static Long COUNT = new Long(0);


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
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String id;
        public String nomeSvagoG;
        public String viaSvagoG;
        public String orariSvagoG;
        public String costoSvagoG;
        public String descrizioneSvagoG;
        public String immagineSvagoG;

        public String getImmagineSvagoG() {
            return immagineSvagoG;
        }

        public void setImmagineSvagoG(String immagineSvagoG) {
            this.immagineSvagoG = immagineSvagoG;
        }

        public String getNomeSvagoG() {
            return nomeSvagoG;
        }

        public void setNomeSvagoG(String nomeSvagoG) {
            this.nomeSvagoG = nomeSvagoG;
        }

        public String getViaSvagoG() {
            return viaSvagoG;
        }

        public void setViaSvagoG(String viaSvagoG) {
            this.viaSvagoG = viaSvagoG;
        }

        public String getOrariSvagoG() {
            return orariSvagoG;
        }

        public void setOrariSvagoG(String orariSvagoG) {
            this.orariSvagoG = orariSvagoG;
        }

        public String getCostoSvagoG() {
            return costoSvagoG;
        }

        public void setCostoSvagoG(String costoSvagoG) {
            this.costoSvagoG = costoSvagoG;
        }

        public String getDescrizioneSvagoG() {
            return descrizioneSvagoG;
        }

        public void setDescrizioneSvagoG(String descrizioneSvagoG) {
            this.descrizioneSvagoG = descrizioneSvagoG;
        }

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
    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeSvagoG(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneSvagoG(e.getText().toString());
        e = iterator.next();
        item.setViaSvagoG(e.getText().toString());
        e = iterator.next();
        item.setOrariSvagoG(e.getText().toString());
        e = iterator.next();
        item.setCostoSvagoG(e.getText().toString());
        return item;
    }
}
