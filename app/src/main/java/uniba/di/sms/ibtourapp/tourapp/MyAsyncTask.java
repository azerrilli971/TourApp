package uniba.di.sms.ibtourapp.tourapp;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uniba.di.sms.ibtourapp.tourapp.dummy.Alberghi;
import uniba.di.sms.ibtourapp.tourapp.dummy.BeBs;
import uniba.di.sms.ibtourapp.tourapp.dummy.Chiese;
import uniba.di.sms.ibtourapp.tourapp.dummy.Diari;
import uniba.di.sms.ibtourapp.tourapp.dummy.Gelaterie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;
import uniba.di.sms.ibtourapp.tourapp.dummy.Musei;
import uniba.di.sms.ibtourapp.tourapp.dummy.Pizzerie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Ristoranti;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoFamiglie;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoGiovani;

public class MyAsyncTask extends AsyncTask {
    private String context;
    public MyAsyncTask(String newContex) {
        this.context = newContex;
    }
    private FirebaseAuth mAuth;

    @Override
    protected Object doInBackground(Object[] objects) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        ref.child(context).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot data : dataSnapshot.getChildren()) {
                    switch(context) {
                        case "Monumenti" :
                            Monumenti.DummyItem item = data.getValue(Monumenti.DummyItem.class);
                            item.setId(data.getKey());
                            int flag = 1;
                            for (Monumenti.DummyItem mus: Monumenti.ITEMS
                            ) {
                                if(mus.id == item.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Monumenti.addItem(new Monumenti.DummyItem(item.id, item.nomeMonumento, item.viaMonumento, item.descrizioneMonumento, item.immagineMonumento));
                                Monumenti.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "B&b" :
                            BeBs.DummyItem beb = data.getValue(BeBs.DummyItem.class);
                            beb.setId(data.getKey());
                            flag = 1;
                            for (Monumenti.DummyItem mus: Monumenti.ITEMS
                            ) {
                                if(mus.id == beb.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                BeBs.addItem(new BeBs.DummyItem(beb.id, beb.nomeBB, beb.viaBB, beb.descrizioneBB, beb.costoBB, beb.immagineBB));
                                BeBs.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Gelaterie":
                            Gelaterie.DummyItem dummyItem = data.getValue(Gelaterie.DummyItem.class);
                            dummyItem.setId(data.getKey());
                            flag = 1;
                            for (Gelaterie.DummyItem mus: Gelaterie.ITEMS
                            ) {
                                if(mus.id == dummyItem.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Gelaterie.addItem(new Gelaterie.DummyItem(dummyItem.id, dummyItem.nomeGelateria, dummyItem.viaGelateria, dummyItem.orariGelateria, dummyItem.descrizioneGelateria, dummyItem.immagine));
                                Gelaterie.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Chiese" :
                            Chiese.DummyItem chiesa = data.getValue(Chiese.DummyItem.class);
                            chiesa.setId(data.getKey());
                            flag = 1;
                            for (Chiese.DummyItem mus: Chiese.ITEMS
                            ) {
                                if(mus.id == chiesa.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Chiese.addItem(new Chiese.DummyItem(chiesa.id, chiesa.nomeChiesa, chiesa.viaChiesa, chiesa.descrizioneChiesa, chiesa.immagineChiesa));
                                Chiese.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Musei" :
                            Musei.DummyItem museo = data.getValue(Musei.DummyItem.class);
                            museo.setId(data.getKey());
                            flag = 1;
                            for (Musei.DummyItem mus: Musei.ITEMS
                                 ) {
                                if(mus.id == museo.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Musei.COUNT = dataSnapshot.getChildrenCount();
                                Musei.addItem(new Musei.DummyItem(museo.id, museo.nomeMuseo, museo.viaMuseo, museo.orariMuseo, museo.descrizioneMuseo, museo.immagineMuseo));
                            }
                            break;
                        case "Pizzerie" :
                            Pizzerie.DummyItem pizzeria = data.getValue(Pizzerie.DummyItem.class);
                            pizzeria.setId(data.getKey());
                            flag = 1;
                            for (Pizzerie.DummyItem mus: Pizzerie.ITEMS
                            ) {
                                if(mus.id == pizzeria.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Pizzerie.addItem(new Pizzerie.DummyItem(pizzeria.id, pizzeria.nomePizzeria, pizzeria.viaPizzeria, pizzeria.orariPizzeria, pizzeria.dettagliPizzeria, pizzeria.immaginePizzeria));
                                Pizzerie.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Ristoranti" :
                            Ristoranti.DummyItem ristorante = data.getValue(Ristoranti.DummyItem.class);
                            ristorante.setId(data.getKey());
                            flag = 1;
                            for (Ristoranti.DummyItem mus: Ristoranti.ITEMS
                            ) {
                                if(mus.id == ristorante.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Ristoranti.addItem(new Ristoranti.DummyItem(ristorante.id, ristorante.nomeRistorante, ristorante.viaRistorante, ristorante.orariRistorante, ristorante.dettagliRistorante, ristorante.immagineRistorante));
                                Ristoranti.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "SvagoGiovani" :
                            SvagoGiovani.DummyItem svagoGiovani = data.getValue(SvagoGiovani.DummyItem.class);
                            svagoGiovani.setId(data.getKey());
                            flag = 1;
                            for (SvagoGiovani.DummyItem mus: SvagoGiovani.ITEMS
                            ) {
                                if(mus.id == svagoGiovani.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                SvagoGiovani.addItem(new SvagoGiovani.DummyItem(svagoGiovani.id, svagoGiovani.nomeSvagoG, svagoGiovani.viaSvagoG, svagoGiovani.orariSvagoG, svagoGiovani.costoSvagoG, svagoGiovani.descrizioneSvagoG, svagoGiovani.immagineSvagoG));
                                SvagoGiovani.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "SvagoFamiglie" :
                            SvagoFamiglie.DummyItem svagoFamiglie = data.getValue(SvagoFamiglie.DummyItem.class);
                            svagoFamiglie.setId(data.getKey());
                            flag = 1;
                            for (SvagoFamiglie.DummyItem mus: SvagoFamiglie.ITEMS
                            ) {
                                if(mus.id == svagoFamiglie.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                SvagoFamiglie.addItem(new SvagoFamiglie.DummyItem(svagoFamiglie.id, svagoFamiglie.nomeSvagoF, svagoFamiglie.viaSvagoF, svagoFamiglie.orariSvagoF, svagoFamiglie.costoSvagoF, svagoFamiglie.descrizioneSvagoF, svagoFamiglie.immagineSvagoF));
                                SvagoGiovani.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Alberghi" :
                            Alberghi.DummyItem albergo = data.getValue(Alberghi.DummyItem.class);
                            albergo.setId(data.getKey());
                            flag = 1;
                            for (Alberghi.DummyItem mus: Alberghi.ITEMS
                            ) {
                                if(mus.id == albergo.id)
                                    flag = 0;
                            }
                            if(flag == 1) {
                                Alberghi.addItem(new Alberghi.DummyItem(albergo.id, albergo.nomeHotel, albergo.viaHotel, albergo.costoHotel, albergo.descrizioneHotel, albergo.immagineHotel));
                                Alberghi.COUNT = dataSnapshot.getChildrenCount();
                            }
                            break;
                        case "Diari" :
                            ref.child(context).child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot temp: dataSnapshot.getChildren()
                                         ) {
                                            Diari.DummyItem diario = new Diari.DummyItem();
                                            diario = temp.getValue(Diari.DummyItem.class);
                                            diario.setId(temp.getKey());
                                            int flag = 1;
                                        for (Diari.DummyItem ite: Diari.ITEMS
                                             ) {
                                            if (ite.getId() == diario.getId())
                                                flag = 0;
                                        }
                                        if (flag == 1) {
                                            Diari.addItem(new Diari.DummyItem(diario.id, diario.ricordo, diario.descrizioneRicordo));
                                            Diari.COUNT = dataSnapshot.getChildrenCount();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        default:
                            break;
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