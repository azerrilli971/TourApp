package uniba.di.sms.ibtourapp.tourapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

import uniba.di.sms.ibtourapp.tourapp.dummy.Monumenti;
import uniba.di.sms.ibtourapp.tourapp.dummy.Musei;

public class CustomListActivity extends AppCompatActivity {

    private final int select_photo = 1;
    ArrayList<String> valori = new ArrayList<>();
    ArrayList<EditText> dummyInfo = new ArrayList<>();
    ImageView gallery_image, galleryDown;
    Musei.DummyItem monumento = new Musei.DummyItem();
    private Uri imageuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    private String download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Bundle b = getIntent().getExtras();
        final String[] testi;
        testi = b.getStringArray("Testi");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        gallery_image = new ImageView(this);
        Button imgButton = new Button(this);
        imgButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        imgButton.setText("Seleziona");
        // Implement click listener over button
        imgButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        // Intent to gallery
                        Intent in = new Intent(Intent.ACTION_PICK);
                        in.setType("image/*");
                        startActivityForResult(in, select_photo);// start
                        // activity
                        // for
                        // result

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
                        ref.child(testi[0]).child("01").setValue(museo);
                        break;
                }

            }
        });
        addTextViews(testi, linearLayout);
        linearLayout.addView(gallery_image);
        linearLayout.addView(imgButton);
        linearLayout.addView(pushButton);
        linearLayout.addView(galleryDown);
        setContentView(linearLayout);
    }

    protected void onActivityResult(int requestcode, int resultcode,
                                    Intent imagereturnintent) {
        super.onActivityResult(requestcode, resultcode, imagereturnintent);
        switch (requestcode) {
            case select_photo:
                if (resultcode == RESULT_OK) {
                    try {

                        imageuri = imagereturnintent.getData();// Get intent
                        // data;
                        Bitmap bitmap = decodeUri(CustomListActivity.this, imageuri, 300);// call
                        // deocde
                        // uri
                        // method
                        // Check if bitmap is not null then set image else show
                        // toast
                        if (bitmap != null) {
                            gallery_image.setImageBitmap(bitmap);
                            uploadImage();
                        }
                            // Set image over
                            // bitmap

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

    // Method that deocde uri into bitmap. This method is necessary to deocde
    // large size images to load over imageview
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

    // Get Original image path
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    private void addTextViews(String[] test, ViewGroup linearLayout) {
        //Adding a LinearLayout with HORIZONTAL orientation
        for (int i = 1; i < test.length; i++) {
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setText(test[i]);
            textView1.setPadding(20, 20, 20, 20); // in pixels (left, top, right, bottom)
            linearLayout.addView(textView1);
            addEditTexts(linearLayout);
        }
    }

    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
    }

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private void addLineSeperator(ViewGroup linearLayout) {
        LinearLayout lineLayout = new LinearLayout(this);
        lineLayout.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        params.setMargins(0, convertDpToPixel(10), 0, convertDpToPixel(10));
        lineLayout.setLayoutParams(params);
        linearLayout.addView(lineLayout);
    }

    private void addEditTexts(ViewGroup linearLayout) {

        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParam);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        linearLayout.addView(editText);
        dummyInfo.add(editText);
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