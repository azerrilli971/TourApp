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
public class Ristoranti {

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
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public  String id;
        public String nomeRistorante;
        public String dettagliRistorante;
        public String viaRistorante;
        public String orariRistorante;

        public String getNomeRistorante() {
            return nomeRistorante;
        }

        public void setNomeRistorante(String nomeRistorante) {
            this.nomeRistorante = nomeRistorante;
        }

        public String getDettagliRistorante() {
            return dettagliRistorante;
        }

        public void setDettagliRistorante(String dettagliRistorante) {
            this.dettagliRistorante = dettagliRistorante;
        }

        public String getViaRistorante() {
            return viaRistorante;
        }

        public void setViaRistorante(String viaRistorante) {
            this.viaRistorante = viaRistorante;
        }

        public String getOrariRistorante() {
            return orariRistorante;
        }

        public void setOrariRistorante(String orariRistorante) {
            this.orariRistorante = orariRistorante;
        }

        public String getImmagineRistorante() {
            return immagineRistorante;
        }

        public void setImmagineRistorante(String immagineRistorante) {
            this.immagineRistorante = immagineRistorante;
        }

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

    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeRistorante(e.getText().toString());
        e = iterator.next();
        item.setDettagliRistorante(e.getText().toString());
        e = iterator.next();
        item.setViaRistorante(e.getText().toString());
        e = iterator.next();
        item.setOrariRistorante(e.getText().toString());
        return item;
    }
}
