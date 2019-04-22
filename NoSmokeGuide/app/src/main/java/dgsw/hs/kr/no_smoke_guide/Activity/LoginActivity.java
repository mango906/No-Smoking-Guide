package dgsw.hs.kr.no_smoke_guide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dgsw.hs.kr.no_smoke_guide.Model.User;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Store.Store;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText usernameEd, passwordEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide(); // Top Bar hide
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this, "userdb", null, 1);

        usernameEd = findViewById(R.id.ed_username);
        passwordEd = findViewById(R.id.ed_password);
    }

    public void login(View v) {
        String username = usernameEd.getText().toString();
        String password = passwordEd.getText().toString();

        if (username.matches("") ||
                password.matches("")) {
            Toast.makeText(this, "입력을 완료해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, password);

        User res = dbHelper.login(user);

        if(res == null){
            Toast.makeText(this, "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
        } else{
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            Store.username = res.getUsername();
            startActivity(i);
        }
    }

    public void register(View v) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
