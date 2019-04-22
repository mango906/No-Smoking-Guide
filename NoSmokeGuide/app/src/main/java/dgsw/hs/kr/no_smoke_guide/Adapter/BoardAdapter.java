package dgsw.hs.kr.no_smoke_guide.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dgsw.hs.kr.no_smoke_guide.Interface.ItemClickListener;
import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> implements ItemClickListener {

    private ArrayList<Board> boards;
    private LayoutInflater mInflater;
    private ItemClickListener listener;

    public BoardAdapter(Context context, ArrayList<Board> boards, ItemClickListener listener) {
        this.boards = boards;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.board_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewHolder.titleTv.setText(boards.get(i).getTitle());
        viewHolder.usernameTv.setText(boards.get(i).getUsername());
        viewHolder.dateTv.setText(sdf.format(boards.get(i).getDate()));
        viewHolder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, i);
        });
    }

    @Override
    public int getItemCount() {
        if(boards.size() != 0){
            return boards.size();
        }
        return 0;
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, usernameTv, dateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
            usernameTv = itemView.findViewById(R.id.username_tv);
            dateTv = itemView.findViewById(R.id.date_tv);

        }
    }
}
