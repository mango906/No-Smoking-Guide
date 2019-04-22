package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class DetailBoardActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private int idx;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_board);

        idx = getIntent().getIntExtra("idx", -1);

        dbHelper = new DBHelper(this, "boarddb", null, 1);

        board = dbHelper.getBoard(idx);

    }
}
