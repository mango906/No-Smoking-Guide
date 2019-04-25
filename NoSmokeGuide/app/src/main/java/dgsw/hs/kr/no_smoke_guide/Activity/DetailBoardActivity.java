package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class DetailBoardActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private int idx;
    private Board board;
    private TextView titleTv, contentTv, usernameTv, dateTv;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_board);

        idx = getIntent().getIntExtra("idx", -1);

        dbHelper = new DBHelper(this, "boarddb", null, 1);
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        titleTv = findViewById(R.id.title_tv);
        contentTv = findViewById(R.id.content_tv);
        usernameTv = findViewById(R.id.username_tv);
        dateTv = findViewById(R.id.date_tv);

        board = dbHelper.getBoard(idx);

        titleTv.setText(board.getTitle());
        contentTv.setText(board.getContent());
        usernameTv.setText(board.getUsername());
        dateTv.setText(sdf.format(board.getDate()));
    }
}
