package dgsw.hs.kr.no_smoke_guide.Model;

public class User {
    private String username;
    private String password;
    private String email;
    private long date;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public User(String username, String password, String email, long date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
