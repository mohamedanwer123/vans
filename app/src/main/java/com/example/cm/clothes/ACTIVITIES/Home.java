package com.example.cm.clothes.ACTIVITIES;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.cm.clothes.R;

public class Home extends AppCompatActivity implements View.OnClickListener {

    TextView textView1 , textView2 , textView3 , textView4;
    CardView cardView1 , cardView2 , cardView3 , cardView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Init();
        Font();
        Animation();
        setup();

    }

    void setup()
    {
        cardView4.setOnClickListener(Home.this);
        cardView3.setOnClickListener(Home.this);
        cardView2.setOnClickListener(Home.this);
        cardView1.setOnClickListener(Home.this);
    }

    void Init()
    {
        textView1 = findViewById(R.id.home_tv1);
        textView2 = findViewById(R.id.home_tv2);
        textView3 = findViewById(R.id.home_tv3);
        textView4 = findViewById(R.id.home_tv4);

        cardView1 = findViewById(R.id.home_cv1);
        cardView2 = findViewById(R.id.home_cv2);
        cardView3 = findViewById(R.id.home_cv3);
        cardView4 = findViewById(R.id.home_cv4);

    }

    void Font()
    {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"myfont.ttf");
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        textView4.setTypeface(typeface);
    }

    void Animation()
    {
        Animation animation = AnimationUtils.loadAnimation(Home.this,R.anim.splash);
        cardView1.setAnimation(animation);
        cardView2.setAnimation(animation);
        cardView3.setAnimation(animation);
        cardView4.setAnimation(animation);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id)
        {
            case R.id.home_cv1 :
                go(1);
                break;
            case R.id.home_cv2 :
                go(2);
                break;
            case R.id.home_cv3 :
                go(3);
                break;
            case R.id.home_cv4 :
                go(4);
                break;
        }
    }

    void go(int x)
    {
        Intent  intent = new Intent(Home.this , Product.class);
        intent.putExtra("flag",x);
        startActivity(intent);
    }
}
