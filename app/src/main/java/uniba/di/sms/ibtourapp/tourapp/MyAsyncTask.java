package uniba.di.sms.ibtourapp.tourapp;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uniba.di.sms.ibtourapp.tourapp.dummy.Chiese;
import uniba.di.sms.ibtourapp.tourapp.dummy.Gelaterie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;
import uniba.di.sms.ibtourapp.tourapp.dummy.Musei;
import uniba.di.sms.ibtourapp.tourapp.dummy.Pizzerie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Ristoranti;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoFamiglie;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoGiovani;

public class MyAsyncTask extends AsyncTask {
    private String context;

    private ImageView imageView;
    public MyAsyncTask(String newContex) {
        this.context = newContex;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child(context).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    switch(context) {
                        case "Monumenti" :
                            Monumenti.DummyItem item = data.getValue(Monumenti.DummyItem.class);
                            item.setId(data.getKey());
                            Monumenti.addItem(new Monumenti.DummyItem(item.id, item.nomeMonumento, item.viaMonumento, item.descrizioneMonumento));
                            break;
                        case "Gelaterie":
                            Gelaterie.DummyItem dummyItem = data.getValue(Gelaterie.DummyItem.class);
                            dummyItem.setId(data.getKey());
                            Gelaterie.addItem(new Gelaterie.DummyItem(dummyItem.id, dummyItem.nomeGelateria, dummyItem.viaGelateria, dummyItem.orariGelateria, dummyItem.descrizioneGelateria, dummyItem.immagine));
                            break;
                        case "Chiese" :
                            Chiese.DummyItem chiesa = data.getValue(Chiese.DummyItem.class);
                            chiesa.setId(data.getKey());
                            Chiese.addItem(new Chiese.DummyItem(chiesa.id, chiesa.nomeChiesa, chiesa.viaChiesa, chiesa.descrizioneChiesa, chiesa.immagineChiesa));
                            break;
                        case "Musei" :
                            Musei.DummyItem museo = data.getValue(Musei.DummyItem.class);
                            museo.setId(data.getKey());
                            int flag = 1;
                            for (Musei.DummyItem mus: Musei.ITEMS
                                 ) {
                                if(mus.id == museo.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Musei.addItem(new Musei.DummyItem(museo.id, museo.nomeMuseo, museo.viaMuseo, museo.orariMuseo, museo.descrizioneMuseo, museo.immagineMuseo));
                            }

                        case "Pizzerie" :
                            Pizzerie.DummyItem pizzeria = data.getValue(Pizzerie.DummyItem.class);
                            pizzeria.setId(data.getKey());
                            Pizzerie.addItem(new Pizzerie.DummyItem(pizzeria.id, pizzeria.nomePizzeria, pizzeria.viaPizzeria, pizzeria.orariPizzeria, pizzeria.dettagliPizzeria, pizzeria.immaginePizzeria));
                        case "Ristoranti" :
                            Ristoranti.DummyItem ristorante = data.getValue(Ristoranti.DummyItem.class);
                            ristorante.setId(data.getKey());
                            Ristoranti.addItem(new Ristoranti.DummyItem(ristorante.id, ristorante.nomeRistorante, ristorante.viaRistorante, ristorante.orariRistorante, ristorante.dettagliRistorante, ristorante.immagineRistorante));
                        case "SvagoGiovani" :
                            SvagoGiovani.DummyItem svagoGiovani = data.getValue(SvagoGiovani.DummyItem.class);
                            svagoGiovani.setId(data.getKey());
                            SvagoGiovani.addItem(new SvagoGiovani.DummyItem(svagoGiovani.id, svagoGiovani.nomeSvagoG, svagoGiovani.viaSvagoG, svagoGiovani.orariSvagoG, svagoGiovani.costoSvagoG, svagoGiovani.descrizioneSvagoG, svagoGiovani.immagineSvagoG));
                        case "SvagoFamiglie" :
                            SvagoFamiglie.DummyItem svagoFamiglie = data.getValue(SvagoFamiglie.DummyItem.class);
                            svagoFamiglie.setId(data.getKey());
                            SvagoFamiglie.addItem(new SvagoFamiglie.DummyItem(svagoFamiglie.id, svagoFamiglie.nomeSvagoF, svagoFamiglie.viaSvagoF, svagoFamiglie.orariSvagoF, svagoFamiglie.costoSvagoF, svagoFamiglie.descrizioneSvagoF, svagoFamiglie.immagineSvagoF));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}