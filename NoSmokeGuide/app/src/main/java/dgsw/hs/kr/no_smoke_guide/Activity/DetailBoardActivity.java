package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dgsw.hs.kr.no_smoke_guide.Adapter.CommentAdapter;
import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.Model.Comment;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class DetailBoardActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private int idx;
    private Board board;
    private RecyclerView.LayoutManager layoutManager;
    private CommentAdapter commentAdapter;
    private TextView titleTv, contentTv, usernameTv, dateTv, countTv;
    private EditText commentEt;
    private Button commentBtn;
    private SimpleDateFormat sdf;
    private RecyclerView recyclerView;
    private ArrayList<Comment> commentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_board);

        idx = getIntent().getIntExtra("idx", -1);

        dbHelper = new DBHelper(this, "db", null, 1);
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        titleTv = findViewById(R.id.title_tv);
        contentTv = findViewById(R.id.content_tv);
        usernameTv = findViewById(R.id.username_tv);
        dateTv = findViewById(R.id.date_tv);
        countTv = findViewById(R.id.count_tv);
        commentEt = findViewById(R.id.comment_et);
        commentBtn = findViewById(R.id.commentBtn);

        board = dbHelper.getBoard(idx);

        titleTv.setText(board.getTitle());
        contentTv.setText(board.getContent());
        usernameTv.setText(board.getUsername());
        dateTv.setText(sdf.format(board.getDate()));

        commentArrayList = dbHelper.getComments(board.getIdx());

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        commentAdapter = new CommentAdapter(commentArrayList, this);
        commentAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(commentAdapter);

        commentBtn.setOnClickListener(v -> {
            String commentText = commentEt.getText().toString();
            Comment comment = new Comment(idx, Store.username, commentText, new Date().getTime());
            long idx = dbHelper.setComment(comment);
            if (idx == -1) {
                Snackbar.make(v, "댓글을 작성에 실패했어요...", Snackbar.LENGTH_SHORT).show();
                return;
            }
            Snackbar.make(v, "댓글을 작성에 성공했어요!", Snackbar.LENGTH_SHORT).show();
            commentAdapter.notifyDataSetChanged();
        });

        countTv.setText("댓글수: " + commentArrayList.size());
    }

}
