package uniba.di.sms.ibtourapp.tourapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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

    private final int PICK_FROM_GALLERY = 1;
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
    String traduzione = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Bundle b = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                        try {
                            if (ActivityCompat.checkSelfPermission(CustomListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(CustomListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                            } else {
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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
                            Musei.COUNT = Musei.COUNT + 1;
                            ref.child(testi[0]).child(Musei.COUNT.toString()).setValue(museo).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            Monumenti.COUNT = Monumenti.COUNT + 1;
                            ref.child(testi[0]).child(Monumenti.COUNT.toString()).setValue(monumento).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            Chiese.COUNT = Chiese.COUNT + 1;
                            ref.child(testi[0]).child(Chiese.COUNT.toString()).setValue(chiesa).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            Spiagge.COUNT = Spiagge.COUNT + 1;
                            ref.child(testi[0]).child(Spiagge.COUNT.toString()).setValue(spiaggia).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                    Toast.makeText(getApplicationContext(), "Item modficato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            SvagoFamiglie.COUNT = SvagoFamiglie.COUNT + 1;
                            ref.child(testi[0]).child(SvagoFamiglie.COUNT.toString()).setValue(svagoF).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(svagoG).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            SvagoGiovani.COUNT = SvagoGiovani.COUNT + 1;
                            ref.child(testi[0]).child(SvagoGiovani.COUNT.toString()).setValue(svagoG).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                    Toast.makeText(getApplicationContext(), "Item modificato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Alberghi.COUNT = Alberghi.COUNT + 1;
                            ref.child(testi[0]).child(Alberghi.COUNT.toString()).setValue(albergo).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            BeBs.COUNT = BeBs.COUNT + 1;
                            ref.child(testi[0]).child(BeBs.COUNT.toString()).setValue(bb).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            Ristoranti.COUNT = Ristoranti.COUNT + 1;
                            ref.child(testi[0]).child(Ristoranti.COUNT.toString()).setValue(ristorante).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        Translate(gelateria.descrizioneGelateria, "it-es");
                        gelateria.setDescrizioneGelateria(traduzione);
                        if(val[0] != "0") {
                            ref.child(testi[0]).child(val[val.length - 1]).setValue(gelateria).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Item salvato con successo!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Gelaterie.COUNT = Gelaterie.COUNT + 1;
                            ref.child(testi[0]).child(Gelaterie.COUNT.toString()).setValue(gelateria).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                            Pizzerie.COUNT = Pizzerie.COUNT + 1;
                            ref.child(testi[0]).child(Pizzerie.COUNT.toString()).setValue(pizzeria).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy ");
                        String datetime = dateformat.format(c.getTime());
                        diario.setDataRicordo(datetime);
                        Diari.COUNT = Diari.COUNT + 1;
                        ref.child("Diari").child(mAuth.getCurrentUser().getUid()).child(Diari.COUNT.toString()).setValue(diario).addOnSuccessListener(new OnSuccessListener<Void>() {
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
            case
                    PICK_FROM_GALLERY:
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

    public String Translate(String textToBeTranslated,String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask = new TranslatorBackgroundTask(this);
        try {
            traduzione = translatorBackgroundTask.execute(textToBeTranslated,languagePair).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return traduzione;
    }

    public void setData(String data){
        traduzione = data;
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
      /*  Intent in = new Intent(Intent.ACTION_PICK);
        in.setType("image/*");
        startActivityForResult(in, select_photo);*/
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item){

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}