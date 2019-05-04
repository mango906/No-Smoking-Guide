package dgsw.hs.kr.no_smoke_guide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import dgsw.hs.kr.no_smoke_guide.Model.User;
import dgsw.hs.kr.no_smoke_guide.R;
import dgsw.hs.kr.no_smoke_guide.Utils.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText usernameEd, passwordEd, passwordChkEd, emailEd;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide(); // Top Bar hide
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this, "db", null, 1);

        usernameEd = findViewById(R.id.ed_username);
        passwordEd = findViewById(R.id.ed_password);
        passwordChkEd = findViewById(R.id.ed_password_chk);
        emailEd = findViewById(R.id.ed_email);
    }

    public void register(View v) {
        String username = usernameEd.getText().toString();
        String password = passwordEd.getText().toString();
        String passwordChk = passwordChkEd.getText().toString();
        String email = emailEd.getText().toString();
        long date = new Date().getTime();

        if (username.matches("") ||
                password.matches("") ||
                passwordChk.matches("") ||
                email.matches("")) {
            Toast.makeText(this, "입력을 완료해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordChk)) {
            Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, password, email, date);

        dbHelper.register(user);

        Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
