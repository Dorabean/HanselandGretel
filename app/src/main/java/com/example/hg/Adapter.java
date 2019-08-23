package com.example.hg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;



/**
 * Created by HAN on 2018. 1. 8..
 */

// extends PagerAdapter 를 하고 나면 빨간줄이 그어지는데 반드시 상속해야할 메소드를 상속하지 않았기 때문.
// 해당 메소드들을 상속해준다
public class Adapter extends PagerAdapter {

    // R.drawable.(사진파일이름)으로 images 배열 생성
    private int[] images = {R.drawable.jim, R.drawable.tae, R.drawable.jimin, R.drawable.jung, R.drawable.bts};
    private LayoutInflater inflater;
    private Context context;

    // 해당 context가 자신의 context 객체와 똑같이 되도록 생성자를 만듬
    public Adapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // object를 LinearLayout 형태로 형변환했을 때 view와 같은지 여부를 반환
//        return view == ((LinearLayout)object); 으로 했을때 오류 나서 View 로 바꿈..
        return view == ((View)object);
    }

    // 각각의 item을 인스턴스 화
    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        //초기화
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.quizimg, container, false);
        ImageView qimg = (ImageView)v.findViewById(R.id.qimg);
        TextView story = (TextView)v.findViewById(R.id.story);
        qimg.setImageResource(images[position]);
        story.setText((position+1)+"스토리~~");
        container.addView(v);
        return v;
    }

    //할당을 해제
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
//        super.destroyItem(container, position, object);
    }
}
