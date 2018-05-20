package com.example.cm.clothes.ACTIVITIES;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cm.clothes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Font();
        Animation();
        linearLayout.setOnClickListener(this);
    }

    void Init()
    {
        imageView = findViewById(R.id.splash_logo);
        textView = findViewById(R.id.splash_title);
        linearLayout = findViewById(R.id.splash_ll);
    }

    void Font()
    {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"myfont.ttf");
        textView.setTypeface(typeface);
    }

    void Animation()
    {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.splash);
        textView.setAnimation(animation);
        imageView.setAnimation(animation);
    }

    @Override
    public void onClick(View view) {

        startActivity(new Intent(MainActivity.this,Home.class));
        finish();
    }
}
