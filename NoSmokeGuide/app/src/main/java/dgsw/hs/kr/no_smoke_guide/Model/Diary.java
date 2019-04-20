package dgsw.hs.kr.no_smoke_guide.Model;

public class Diary {
    private String date;
    private String felling;

    public Diary() {
    }

    public Diary(String date, String felling) {
        this.date = date;
        this.felling = felling;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFelling() {
        return felling;
    }

    public void setFelling(String felling) {
        this.felling = felling;
    }
}
