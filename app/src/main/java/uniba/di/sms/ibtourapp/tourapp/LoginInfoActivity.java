package uniba.di.sms.ibtourapp.tourapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInfoActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        findViewById(R.id.textLoginInfoPoint).setVisibility(View.INVISIBLE);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.erroreMail));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.mailDiversa));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.inserirePsw));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.lunghezzaMinima));
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    UsersDbHelper dbHelper = new UsersDbHelper(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getReadableDatabase();

                    String[] projection = {
                            BaseColumns._ID,
                            UsersList.FeedEntry.COLUMN_NAME_TITLE,
                            UsersList.FeedEntry.COLUMN_NAME_SUBTITLE
                    };

                    String selection = UsersList.FeedEntry.COLUMN_NAME_TITLE + " = ?";
                    String[] selectionArgs = { mAuth.getCurrentUser().getUid() };

                    String sortOrder =
                            UsersList.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

                    Cursor cursor = db.query(
                            UsersList.FeedEntry.TABLE_NAME,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,
                            sortOrder
                    );

                    if(cursor.getCount() == 0) {
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        String user = mAuth.getCurrentUser().getUid();
                        ContentValues values = new ContentValues();

                        values.put(UsersList.FeedEntry.COLUMN_NAME_TITLE, user);
                        values.put(UsersList.FeedEntry.COLUMN_NAME_SUBTITLE, 1);

                        long newRowId = database.insert(UsersList.FeedEntry.TABLE_NAME, null, values);
                        if(newRowId != 0) {
                            Toast.makeText(getApplicationContext(), "Utente registrato correttamente", Toast.LENGTH_LONG).show();
                        }
                    }
                    Intent intent = new Intent(LoginInfoActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            UsersDbHelper dbHelper = new UsersDbHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    BaseColumns._ID,
                    UsersList.FeedEntry.COLUMN_NAME_TITLE,
                    UsersList.FeedEntry.COLUMN_NAME_SUBTITLE
            };

            String selection = UsersList.FeedEntry.COLUMN_NAME_TITLE + " = ?";
            String[] selectionArgs = { mAuth.getCurrentUser().getUid() };

            String sortOrder =
                    UsersList.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

            Cursor cursor = db.query(
                    UsersList.FeedEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,
                    sortOrder
            );

            while(cursor.moveToNext()) {
                int itemId = cursor.getInt(
                        cursor.getColumnIndexOrThrow(UsersList.FeedEntry.COLUMN_NAME_SUBTITLE));
                if(itemId == 0) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
            cursor.close();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this, SignUpInfoActivity.class));
                break;
            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}