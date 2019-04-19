package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dgsw.hs.kr.no_smoke_guide.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide(); // Top Bar hide
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_login);
    }

    public void register(View v) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
