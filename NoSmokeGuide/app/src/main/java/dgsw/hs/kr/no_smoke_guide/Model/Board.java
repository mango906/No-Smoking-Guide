package dgsw.hs.kr.no_smoke_guide.Model;

public class Board {
    private int idx;
    private String username;
    private String title;
    private String content;
    private long date;

    public Board() {
    }

    public Board(String username, String title, String content, long date) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Board(int idx, String username, String title, String content, long date) {
        this.idx = idx;
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
