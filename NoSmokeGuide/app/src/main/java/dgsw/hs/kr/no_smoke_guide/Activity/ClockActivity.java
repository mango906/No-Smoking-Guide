package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import dgsw.hs.kr.no_smoke_guide.Lib.MyAnalogClock;
import dgsw.hs.kr.no_smoke_guide.Model.User;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class ClockActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private User user;
    private TextView startDate, dateCnt, dateMoney, life;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        dbHelper = new DBHelper(this, "db", null, 1);
        life = findViewById(R.id.life);
        startDate = findViewById(R.id.start_date);
        dateCnt = findViewById(R.id.date_cnt);
        dateMoney = findViewById(R.id.date_money);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        user = dbHelper.getInfo(Store.username);

        long date = new Date().getTime() - user.getDate();
        int hour = 0;
        int min = (int) date / 1000 / 60;
        if (min >= 60) {
            hour += min / 60;
            min -= hour * 60;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);


        MyAnalogClock vectorClock = findViewById(R.id.clock);

        vectorClock.setCalendar(calendar)
                .setDiameterInDp(300.0f)
                .setOpacity(1.0f)
                .setShowSeconds(false)
                .setColor(R.color.colorPrimary);
        int days = (int) ((new Date().getTime() - user.getDate()) / (1000 * 60 * 60 * 24)) + 1;

        startDate.setText(startDate.getText() + " : " + simpleDateFormat.format(user.getDate()));
        dateCnt.setText(dateCnt.getText() + " : " + days + " 일 째");
        dateMoney.setText(dateMoney.getText() + " : " + String.valueOf(4500 * days) + "원 세이브");

        int lifeDay = 0;
        int lifeHour = 0;
        int lifeMin = (days - 1) * 55 + hour * 2;
        if (lifeMin >= 60) {
            lifeHour = lifeMin / 60;
            lifeMin -= 60 * lifeHour;
        }
        if(lifeHour >= 60){
            lifeDay = lifeHour / 60;
            lifeHour -= 60 * lifeDay;
        }
        life.setText(String.valueOf(lifeDay) + "일 " + String.valueOf(lifeHour) + "시간 " + String.valueOf(lifeMin) + "분 수명이 연장되었습니다.");
    }
}
