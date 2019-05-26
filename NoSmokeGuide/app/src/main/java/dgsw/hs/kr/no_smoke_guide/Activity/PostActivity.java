package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import dgsw.hs.kr.no_smoke_guide.Adapter.BoardAdapter;
import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class PostActivity extends AppCompatActivity {

    EditText titleEt, contentEt;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        dbHelper = new DBHelper(this, "db", null, 1);

        titleEt = findViewById(R.id.title_et);
        contentEt = findViewById(R.id.content_et);

    }

    public void post(View v) {
        String username = Store.username;
        String title = titleEt.getText().toString();
        String content = titleEt.getText().toString();
        long date = new Date().getTime();

        Board board = new Board(username, title, content, date);

        long idx = dbHelper.post(board);

        if (idx == -1) {
            Snackbar.make(v, "글 작성에 실패했어요...", Snackbar.LENGTH_SHORT).show();
            return;
        }
        Snackbar.make(v, "글 작성에 성공했어요!", Snackbar.LENGTH_SHORT).show();

        finish();
    }
}
