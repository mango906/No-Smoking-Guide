package dgsw.hs.kr.no_smoke_guide.Model;

import java.util.ArrayList;

public class DetailBoard {
    private Board board;
    private ArrayList<Comment> comments;

    public DetailBoard() {
    }

    public DetailBoard(Board board, ArrayList<Comment> comments) {
        this.board = board;
        this.comments = comments;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
