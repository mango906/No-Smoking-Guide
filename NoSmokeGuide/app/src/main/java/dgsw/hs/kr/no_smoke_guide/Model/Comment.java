package dgsw.hs.kr.no_smoke_guide.Model;

public class Comment {
    private int idx;
    private int boardIdx;
    private String username;
    private String content;
    private long date;

    public Comment() {}

    public Comment(int boardIdx, String username, String content, long date) {
        this.boardIdx = boardIdx;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
