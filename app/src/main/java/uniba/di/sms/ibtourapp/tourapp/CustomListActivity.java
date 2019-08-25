package uniba.di.sms.ibtourapp.tourapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

import uniba.di.sms.ibtourapp.tourapp.dummy.Alberghi;
import uniba.di.sms.ibtourapp.tourapp.dummy.BeBs;
import uniba.di.sms.ibtourapp.tourapp.dummy.Chiese;
import uniba.di.sms.ibtourapp.tourapp.dummy.Diari;
import uniba.di.sms.ibtourapp.tourapp.dummy.Gelaterie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;
import uniba.di.sms.ibtourapp.tourapp.dummy.Musei;
import uniba.di.sms.ibtourapp.tourapp.dummy.Pizzerie;
import uniba.di.sms.ibtourapp.tourapp.dummy.Ristoranti;
import uniba.di.sms.ibtourapp.tourapp.dummy.Spiagge;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoFamiglie;
import uniba.di.sms.ibtourapp.tourapp.dummy.SvagoGiovani;

public class CustomListActivity extends AppCompatActivity {

    private final int select_photo = 1;
    ArrayList<EditText> dummyInfo = new ArrayList<>();
    ImageView gallery_image;
    private Uri imageuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    private String download;
    String[] val = {"0"};
    String path = "01";
    private FirebaseAuth mAuth;
    Long counter = new Long(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Bundle b = getIntent().getExtras();
        final String[] testi;
        testi = b.getStringArray("Testi");
        if(b.size() > 1) {
            val = b.getStringArray("Valori");
        }
        mAuth = FirebaseAuth.getInstance();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        gallery_image = new ImageView(this);
        Button imgButton = new Button(this);
        imgButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        imgButton.setText("Seleziona");
        imgButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Intent in = new Intent(Intent.ACTION_PICK);
                        in.setType("image/*");
                        startActivityForResult(in, select_photo);
                    }
                });
        Button pushButton = new Button(this);
        pushButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        pushButton.setText("Salva");
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();

                switch (testi[0]) {
                    case "Musei":
                        Musei.DummyItem museo = new Musei.DummyItem();
                        museo = Musei.addItemList(dummyInfo);
                        museo.setImmagineMuseo(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(museo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item aggiornato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {

                            ref.child(testi[0]).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    counter  = dataSnapshot.getChildrenCount();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            ref.child(testi[0]).child(counter.toString()).setValue(museo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Monumenti":
                        Monumenti.DummyItem monumento = new Monumenti.DummyItem();
                        monumento = Monumenti.addItemList(dummyInfo);
                        monumento.setImmagineMonumento(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(monumento).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(monumento).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Chiese":
                        Chiese.DummyItem chiesa = new Chiese.DummyItem();
                        chiesa = Chiese.addItemList(dummyInfo);
                        chiesa.setImmagineChiesa(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(chiesa).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(chiesa).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Spiagge":
                        Spiagge.DummyItem spiaggia = new Spiagge.DummyItem();
                        spiaggia = Spiagge.addItemList(dummyInfo);
                        spiaggia.setImmagineSpiaggia(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(spiaggia).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(spiaggia).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "SvagoFamiglie":
                        SvagoFamiglie.DummyItem svagoF = new SvagoFamiglie.DummyItem();
                        svagoF = SvagoFamiglie.addItemList(dummyInfo);
                        svagoF.setImmagineSvagoF(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(svagoF).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(svagoF).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "SvagoGiovani":
                        SvagoGiovani.DummyItem svagoG = new SvagoGiovani.DummyItem();
                        svagoG = SvagoGiovani.addItemList(dummyInfo);
                        svagoG.setImmagineSvagoG(download);
                        ref.child(testi[0]).child("01").setValue(svagoG);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(svagoG).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(svagoG).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Alberghi":
                        Alberghi.DummyItem albergo = new Alberghi.DummyItem();
                        albergo = Alberghi.addItemList(dummyInfo);
                        albergo.setImmagineHotel(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(albergo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(albergo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "BeB":
                        BeBs.DummyItem bb = new BeBs.DummyItem();
                        bb = BeBs.addItemList(dummyInfo);
                        bb.setImmagineBB(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(bb).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(bb).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Ristoranti":
                        Ristoranti.DummyItem ristorante = new Ristoranti.DummyItem();
                        ristorante = Ristoranti.addItemList(dummyInfo);
                        ristorante.setImmagineRistorante(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(ristorante).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(ristorante).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Gelaterie":
                        Gelaterie.DummyItem gelateria = new Gelaterie.DummyItem();
                        gelateria = Gelaterie.addItemList(dummyInfo);
                        gelateria.setImmagine(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(gelateria).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(gelateria).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        finish();
                        break;
                    case "Pizzerie":
                        Pizzerie.DummyItem pizzeria = new Pizzerie.DummyItem();
                        pizzeria = Pizzerie.addItemList(dummyInfo);
                        pizzeria.setImmaginePizzeria(download);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(pizzeria).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            ref.child(testi[0]).child(path).setValue(pizzeria).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                        finish();
                        break;
                    case "Diari" :
                        Diari.DummyItem diario = new Diari.DummyItem();
                        diario = Diari.addItemList(dummyInfo);
                        diario.setRicordo(download);
                        ref.child("Diari").child(mAuth.getCurrentUser().getUid()).child("01").setValue(diario).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();
                        break;
                }

            }
        });
        addTextViews(testi, linearLayout);
        linearLayout.addView(gallery_image);
        linearLayout.addView(imgButton);
        linearLayout.addView(pushButton);
        setContentView(linearLayout);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void onActivityResult(int requestcode, int resultcode,
                                    Intent imagereturnintent) {
        super.onActivityResult(requestcode, resultcode, imagereturnintent);
        switch (requestcode) {
            case select_photo:
                if (resultcode == RESULT_OK) {
                    try {
                        imageuri = imagereturnintent.getData();
                        Bitmap bitmap = decodeUri(CustomListActivity.this, imageuri, 300);
                        if (bitmap != null) {
                            gallery_image.setImageBitmap(bitmap);
                            uploadImage();
                        }
                        else
                            Toast.makeText(CustomListActivity.this,
                                    "Error while decoding image.",
                                    Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                        Toast.makeText(CustomListActivity.this, "File not found.",
                                Toast.LENGTH_SHORT).show();


                    }

                }
        }
    }

    public static Bitmap decodeUri(Context context, Uri uri,
                                   final int requiredSize) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o2);
    }

    private void addTextViews(String[] test, ViewGroup linearLayout) {
        for (int i = 1; i < test.length; i++) {
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setText(test[i]);
            textView1.setPadding(20, 20, 20, 20);
            linearLayout.addView(textView1);
            EditText editText = addEditTexts(linearLayout);
            if(val[0] != "0") {
                if(val[i-1] != null) {
                    editText.setText(val[i-1]);
                }
            }
        }
    }
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
    private EditText addEditTexts(ViewGroup linearLayout) {

        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParam);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        linearLayout.addView(editText);
        dummyInfo.add(editText);
        return editText;
    }

    private void uploadImage() {
        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url = uri.getResult();
                            Toast.makeText(CustomListActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            download = url.toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(CustomListActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}