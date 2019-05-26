package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import dgsw.hs.kr.no_smoke_guide.Lib.MyVectorClock;
import dgsw.hs.kr.no_smoke_guide.R;

public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -2);

        MyVectorClock vectorClock = findViewById(R.id.clock);

        vectorClock.setCalendar(calendar)
                .setDiameterInDp(400.0f)
                .setOpacity(1.0f)
                .setShowSeconds(true)
                .setColor(R.color.colorPrimary);
    }
}
