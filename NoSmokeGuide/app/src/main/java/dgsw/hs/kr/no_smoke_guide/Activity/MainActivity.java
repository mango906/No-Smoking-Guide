package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import dgsw.hs.kr.no_smoke_guide.Model.User;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class MainActivity extends AppCompatActivity {

    private String username;
    private DBHelper dbHelper;
    private User user;
    private TextView usernameTv, joinedTv, noSmokeDay, saveTv;
    private final int SMOKE_MONEY = 4500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

        dbHelper = new DBHelper(this, "db", null, 1);

        usernameTv = findViewById(R.id.username_tv);
        joinedTv = findViewById(R.id.joined_tv);
        noSmokeDay = findViewById(R.id.no_smoke_day);
        saveTv = findViewById(R.id.save_tv);

        username = Store.username;

        user = dbHelper.getInfo(username);

        String boldUsername = "<b>" +  username + "</b> ";

        usernameTv.setText(Html.fromHtml(boldUsername) + "님의 금연 상황");
        joinedTv.setText(dateSdf.format(user.getDate()));
        int days = (int) ((new Date().getTime() - user.getDate()) / (1000 * 60 * 60 * 24)) + 1;
        noSmokeDay.setText("금연 " + Integer.toString(days) + "일차");
        saveTv.setText("저축한 금액 : " + days * SMOKE_MONEY);
    }

    public void smokeClock(View v){
        Intent i = new Intent(MainActivity.this, ClockActivity.class);
        startActivity(i);
    }

    public void smokeDiary(View v){
        Intent i = new Intent(MainActivity.this, DiaryActivity.class);
        startActivity(i);
    }

    public void smokeBoard(View v){
        Intent i = new Intent(MainActivity.this, BoardActivity.class);
        startActivity(i);
    }
}
