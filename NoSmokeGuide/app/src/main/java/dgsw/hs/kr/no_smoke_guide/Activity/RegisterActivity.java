package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dgsw.hs.kr.no_smoke_guide.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide(); // Top Bar hide
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_register);
    }
}
