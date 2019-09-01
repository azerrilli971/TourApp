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
public class Alberghi {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static Integer COUNT = 0;

    static {
        // Add some sample items.
        addItem(new DummyItem("1", "Nicolaus", "via we", "molto carino", "iodmsklÃ²", "nskla"));
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position,String nomeHotel, String viaHotel, String costoHotel, String descrizioneHotel, String immagineHotel) {
        return new DummyItem(String.valueOf(position), nomeHotel, viaHotel, costoHotel,descrizioneHotel,immagineHotel);
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
        public String nomeHotel;
        public String viaHotel;
        public String costoHotel;
        public String descrizioneHotel;

        public String getNomeHotel() {
            return nomeHotel;
        }

        public void setNomeHotel(String nomeHotel) {
            this.nomeHotel = nomeHotel;
        }

        public String getViaHotel() {
            return viaHotel;
        }

        public void setViaHotel(String viaHotel) {
            this.viaHotel = viaHotel;
        }

        public String getCostoHotel() {
            return costoHotel;
        }

        public void setCostoHotel(String costoHotel) {
            this.costoHotel = costoHotel;
        }

        public String getDescrizioneHotel() {
            return descrizioneHotel;
        }

        public void setDescrizioneHotel(String descrizioneHotel) {
            this.descrizioneHotel = descrizioneHotel;
        }

        public String getImmagineHotel() {
            return immagineHotel;
        }

        public void setImmagineHotel(String immagineHotel) {
            this.immagineHotel = immagineHotel;
        }

        public String immagineHotel;

        public DummyItem(String id, String nomeHotel, String viaHotel, String costoHotel, String descrizioneHotel, String immagineHotel) {
            this.id = id;
            this.nomeHotel = nomeHotel;
            this.viaHotel = viaHotel;
            this.costoHotel = costoHotel;
            this.descrizioneHotel=descrizioneHotel;
            this.immagineHotel=immagineHotel;
        }

        public DummyItem(){}
        @Override
        public String toString() {
            return nomeHotel;
        }
    }
    public static DummyItem addItemList(ArrayList<EditText> lista) {
        DummyItem item = new DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeHotel(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneHotel(e.getText().toString());
        e = iterator.next();
        item.setViaHotel(e.getText().toString());
        e = iterator.next();
        item.setCostoHotel(e.getText().toString());
        return item;
    }
}
