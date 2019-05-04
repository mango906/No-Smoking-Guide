package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

import dgsw.hs.kr.no_smoke_guide.Model.Diary;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class DiaryActivity extends AppCompatActivity {

    private String date;
    private CalendarView calendarView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        dbHelper = new DBHelper(this, "db", null, 1);

        calendarView = findViewById(R.id.calendar_view);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
                Diary diary = dbHelper.getDiary(date);
                if (diary == null) {
                    final CharSequence[] items = {"기분좋음", "보통임", "나쁨"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this);
                    builder.setTitle("기분을 선택해주세요");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Diary d = new Diary(date, items[item].toString());
                            dbHelper.setDiary(d);
                            Toast.makeText(DiaryActivity.this, items[item], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DiaryActivity.this).create();
                    alertDialog.setTitle(date + "기분");
                    alertDialog.setMessage(diary.getFelling());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();// use dismiss to cancel alert dialog
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }
}
