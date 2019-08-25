package com.example.hg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int stagelevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView cookie = (ImageView) findViewById(R.id.cookie);
        TextView stage = (TextView) findViewById(R.id.stage);
        ImageView logo = (ImageView)findViewById(R.id.logo);
        TextView cur = (TextView) findViewById(R.id.cur);
        TextView goal = (TextView) findViewById(R.id.goal);
        TextView gpscur = (TextView) findViewById(R.id.gpscur);
        TextView gpsgoal = (TextView) findViewById(R.id.gpsgoal);

        Button key = (Button) findViewById(R.id.key) ;
        Button question = (Button) findViewById(R.id.question) ;
        ImageButton dod = (ImageButton)findViewById(R.id.dod);

        //gsp 설정 해줘야함
        gpscur.setText(" 12345"+" . "+"233");
        gpsgoal.setText(" 19845"+" . "+"4423");

        //단계설정 database에서 가져와야함
        stagelevel=0;
        stage.setText(String.valueOf(stagelevel));

        //문제확인버튼 클릭시
        question.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(),quiz.class);
                //startActivityForResult(intent,sub);//액티비티 띄우기
                Intent intent = new Intent(MainActivity.this, quiz.class);
                startActivity(intent);
            }
        });

        //돋보기 버튼 클릭시
        dod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClassifierActivity.class);
                startActivity(intent);
            }
        });

        //키제출 버튼 클릭시
        key.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnPopupClick(view);
            }
        });

    }



    //정답입력 팝업 창
    public void mOnPopupClick(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("정답을 입력하세요");
        alert.setMessage("");


        final EditText name = new EditText(this);
        alert.setView(name);

        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String answer = name.getText().toString();
                if(answer.equalsIgnoreCase("정답"))
                {
                    Toast.makeText(getApplicationContext(),"정답입니다!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"틀렸습니다!",Toast.LENGTH_LONG).show();
                }
            }
        });

        alert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }


}
