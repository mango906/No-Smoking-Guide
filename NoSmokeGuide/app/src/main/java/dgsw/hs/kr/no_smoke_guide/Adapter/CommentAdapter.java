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

import dgsw.hs.kr.no_smoke_guide.Model.Comment;
import dgsw.hs.kr.no_smoke_guide.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private ArrayList<Comment> commentArrayList;
    private LayoutInflater mInflater;

    public CommentAdapter(ArrayList<Comment> commentArrayList, Context context) {
        this.commentArrayList = commentArrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.comment_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewHolder.writerTv.setText(commentArrayList.get(i).getUsername());
        viewHolder.contentTv.setText(commentArrayList.get(i).getContent());
        viewHolder.dateTv.setText(sdf.format(commentArrayList.get(i).getDate()));
    }

    @Override
    public int getItemCount() {
        if(commentArrayList.size() != 0){
            return commentArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView writerTv, contentTv, dateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            writerTv = itemView.findViewById(R.id.writer_tv);
            contentTv = itemView.findViewById(R.id.content_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
        }
    }
}
