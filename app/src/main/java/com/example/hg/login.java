package com.example.hg;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    public static final int sub = 1001;

    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    private EditText idid;
    private EditText pwpw;

    private String id = "";
    private String pw = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        Button log = (Button) findViewById(R.id.register) ;
        Button join = (Button) findViewById(R.id.join) ;
        idid = (EditText) findViewById(R.id.id);
        pwpw = (EditText) findViewById(R.id.pw);

        log.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=idid.getText().toString();
                pw=pwpw.getText().toString();
                if(isValidEmail() && isValidPasswd()) {
                    loginUser(id, pw);
                }

            }
        });

        join.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=idid.getText().toString();
                pw=pwpw.getText().toString();
                Intent intent = new Intent(getApplicationContext(),join.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
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

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(login.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(intent, sub);//액티비티 띄우기
                        } else {
                            // 로그인 실패
                            Toast.makeText(login.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
