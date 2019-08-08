package uniba.di.sms.ibtourapp.tourapp;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import uniba.di.sms.ibtourapp.tourapp.dummy.Gelaterie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;

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
                    if(context == "Monumenti") {
                        Monumenti.DummyItem item = data.getValue(Monumenti.DummyItem.class);
                        Monumenti.addItem(new Monumenti.DummyItem(item.id, item.nomeMonumento, item.viaMonumento, item.descrizioneMonumento));
                    } else {
                        Gelaterie.DummyItem dummyItem = data.getValue(Gelaterie.DummyItem.class);
                        Gelaterie.addItem(new Gelaterie.DummyItem(dummyItem.id, dummyItem.nomeGelateria, dummyItem.viaGelateria, dummyItem.orariGelateria, dummyItem.descrizioneGelateria, dummyItem.immagine));
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