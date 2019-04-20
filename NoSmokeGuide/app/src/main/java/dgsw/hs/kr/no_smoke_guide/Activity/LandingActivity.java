package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dgsw.hs.kr.no_smoke_guide.R;

public class LandingActivity extends AppCompatActivity {

    private static int TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide(); // Top Bar hide
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_landing);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME);
    }
}
