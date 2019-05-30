package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dgsw.hs.kr.no_smoke_guide.Adapter.CommentAdapter;
import dgsw.hs.kr.no_smoke_guide.Interface.ItemClickListener;
import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.Model.Comment;
import dgsw.hs.kr.no_smoke_guide.Model.DetailBoard;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class DetailBoardActivity extends AppCompatActivity implements ItemClickListener {

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
    private ArrayList<Comment> commentArrayList, tempArrayList;

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
        tempArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        commentAdapter = new CommentAdapter(commentArrayList, this, this);
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
//            commentArrayList = dbHelper.getComments(board.getIdx());
            tempArrayList.clear();
            tempArrayList.addAll(commentArrayList);
            tempArrayList.add(comment);
            commentArrayList.clear();
            commentArrayList.addAll(tempArrayList);
            commentAdapter.notifyDataSetChanged();
            countTv.setText("댓글수: " + commentArrayList.size());
            commentEt.setText("");
        });

        countTv.setText("댓글수: " + commentArrayList.size());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public void updateForm(View v){
        Intent i = new Intent(DetailBoardActivity.this, PostActivity.class);
        i.putExtra("type", 1);
        i.putExtra("idx", idx);
        startActivity(i);
    }

    public void deleteForm(View v) {
        dbHelper.deleteBoard(idx);
        Intent i = new Intent(DetailBoardActivity.this, BoardActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(View v, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("댓글");
        builder.setMessage("삭제하시겠습니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long commentIdx = dbHelper.deleteComment(position);
                if (commentIdx == -1) {
                    Snackbar.make(v, "댓글을 삭제에 실패했어요...", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Snackbar.make(v, "댓글을 삭제에 성공했어요!", Snackbar.LENGTH_SHORT).show();
                commentArrayList.clear();
                commentArrayList.addAll(dbHelper.getComments(idx));
                commentAdapter.notifyDataSetChanged();
                countTv.setText("댓글 수: " + commentArrayList.size());
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
