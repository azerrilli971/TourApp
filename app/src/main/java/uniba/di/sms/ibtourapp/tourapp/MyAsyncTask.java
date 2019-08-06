package uniba.di.sms.ibtourapp.tourapp;

import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uniba.di.sms.ibtourapp.tourapp.dummy.DummyContent;

public class MyAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    DummyContent.DummyItem item = data.getValue(DummyContent.DummyItem.class);
                    DummyContent.addItem(new DummyContent.DummyItem(item.id, item.nomeMonumento, item.viaMonumento, item.descrizioneMonumento));
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