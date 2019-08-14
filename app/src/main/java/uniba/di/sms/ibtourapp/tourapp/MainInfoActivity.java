package uniba.di.sms.ibtourapp.tourapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class MainInfoActivity extends AppCompatActivity {
    private CardView menuEsploraCV, menuDiarioCV, menuCouponCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info);
    }
}
