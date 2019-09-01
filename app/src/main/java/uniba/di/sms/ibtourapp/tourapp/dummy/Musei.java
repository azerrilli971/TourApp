package uniba.di.sms.ibtourapp.tourapp.dummy;

import android.widget.EditText;

import com.google.android.gms.tasks.Task;

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
public class Musei {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static Long COUNT = new Long(0);

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        COUNT++;
    }

    public static Musei.DummyItem addItemList(ArrayList<EditText> lista) {
        Musei.DummyItem item = new Musei.DummyItem();
        Iterator<EditText> iterator = lista.iterator();
        EditText e = iterator.next();
        item.setNomeMuseo(e.getText().toString());
        e = iterator.next();
        item.setDescrizioneMuseo(e.getText().toString());
        e = iterator.next();
        item.setViaMuseo(e.getText().toString());
        e = iterator.next();
        item.setOrariMuseo(e.getText().toString());
        return item;
    }

    private static DummyItem createDummyItem(int position, String nomeMuseo, String viaMuseo, String orariMuseo, String descrizioneMuseo, String immagineMuseo) {
        return new DummyItem(String.valueOf(position), nomeMuseo, viaMuseo, orariMuseo, descrizioneMuseo, immagineMuseo);
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

        public String getNomeMuseo() {
            return nomeMuseo;
        }

        public void setNomeMuseo(String nomeMuseo) {
            this.nomeMuseo = nomeMuseo;
        }

        public String getViaMuseo() {
            return viaMuseo;
        }

        public void setViaMuseo(String viaMuseo) {
            this.viaMuseo = viaMuseo;
        }

        public String getImmagineMuseo() {
            return immagineMuseo;
        }

        public void setImmagineMuseo(String immagineMuseo) {
            this.immagineMuseo = immagineMuseo;
        }

        public String getDescrizioneMuseo() {
            return descrizioneMuseo;
        }

        public void setDescrizioneMuseo(String descrizioneMuseo) {
            this.descrizioneMuseo = descrizioneMuseo;
        }

        public String getOrariMuseo() {
            return orariMuseo;
        }

        public void setOrariMuseo(String orariMuseo) {
            this.orariMuseo = orariMuseo;
        }

        public String nomeMuseo;
        public String viaMuseo;
        public String immagineMuseo;
        public String descrizioneMuseo;
        public String orariMuseo;

        public DummyItem(String id, String nomeMuseo, String viaMuseo, String orariMuseo, String descrizioneMuseo, String immagineMuseo) {
            this.id = id;
            this.nomeMuseo = nomeMuseo;
            this.viaMuseo = viaMuseo;
            this.orariMuseo = orariMuseo;
            this.descrizioneMuseo = descrizioneMuseo;
            this.immagineMuseo = immagineMuseo;
        }
        public DummyItem(){}

        @Override
        public String toString() {
            return nomeMuseo;
        }
    }
}
