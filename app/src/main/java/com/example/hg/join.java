package com.example.hg;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class join extends AppCompatActivity {

    public static final int sub = 1001;
    private FirebaseAuth firebaseAuth;

    private EditText idid;
    private EditText pwpw;

    private String id="";
    private String pw="";
    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        Button register = (Button) findViewById(R.id.register);
        final EditText idid = (EditText)findViewById(R.id.id);
        final EditText pwpw = (EditText)findViewById(R.id.pw);

        Intent intent = getIntent();//데이터통신

        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = idid.getText().toString();
                pw = pwpw.getText().toString();
                if(isValidEmail() && isValidPasswd()) {
                    createUser(id, pw);
                }
            }

        });

    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(join.this, R.string.success_signup, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivityForResult(intent, sub);//액티비티 띄우기
                        } else {
                            // 회원가입 실패
                            Toast.makeText(join.this, R.string.failed_signup, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 이메일 유효성 검사
    private boolean isValidEmail() {
       if (id.isEmpty()) {
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
            // 이메일 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
       if (pw.isEmpty()) {
            // 비밀번호 공백
            return false;
        } else if (!PASSWORD_PATTERN.matcher(pw).matches()) {
            // 비밀번호 형식 불일치
            return false;
        } else {
            return true;
        }
    }

}


