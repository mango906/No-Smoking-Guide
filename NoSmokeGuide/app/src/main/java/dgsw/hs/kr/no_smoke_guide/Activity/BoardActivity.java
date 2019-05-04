package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import dgsw.hs.kr.no_smoke_guide.Adapter.BoardAdapter;
import dgsw.hs.kr.no_smoke_guide.Interface.ItemClickListener;
import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class BoardActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BoardAdapter boardAdapter;
    private ArrayList<Board> boards;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(BoardActivity.this, PostActivity.class);
            startActivity(i);
        });

        dbHelper = new DBHelper(this, "db", null, 1);

        boards = dbHelper.getBoard();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        boardAdapter = new BoardAdapter(this, boards, this);

        recyclerView.setAdapter(boardAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent i = new Intent(BoardActivity.this, DetailBoardActivity.class);
        i.putExtra("idx", boards.get(position).getIdx());
        startActivity(i);
    }
}
