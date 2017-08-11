package com.example.smile;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class ValueAnimaltionActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btMove;
    private Button btScale;
    private Button btTou;
    private Button btZhuan;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animaltion);
        initView();
    }

    private void initView() {
        btMove=(Button)findViewById(R.id.bt_move);
        btMove.setOnClickListener(this);
        btScale=(Button)findViewById(R.id.bt_scale);
        btScale.setOnClickListener(this);
        btTou=(Button)findViewById(R.id.bt_touming);
        btTou.setOnClickListener(this);
        btZhuan=(Button)findViewById(R.id.bt_zhuan);
        btZhuan.setOnClickListener(this);

        iv=(ImageView)findViewById(R.id.imageView2);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.bt_move:
              move1();
              break;
          case R.id.bt_scale:
              scale();
              break;
          case R.id.bt_touming:

              break;
          case R.id.bt_zhuan:
              round2();
              break;
      }
    }

    private void round() {
        ObjectAnimator romotion=ObjectAnimator.ofFloat(iv,"rotationX",0.0f,360.0f);
        romotion.setRepeatCount(3);
        romotion.setRepeatMode(ObjectAnimator.REVERSE);
        romotion.setDuration(1000);
        romotion.start();
    }

    private void round2() {
        ObjectAnimator romotion=ObjectAnimator.ofFloat(iv,"rotation",0.0f,360.0f);
        romotion.setRepeatCount(3);
        romotion.setRepeatMode(ObjectAnimator.REVERSE);
        romotion.setDuration(1000);
        romotion.start();
    }

    private void scale() {
        PropertyValuesHolder y=PropertyValuesHolder.ofFloat("y",1.0f,0.3f);
        PropertyValuesHolder x=PropertyValuesHolder.ofFloat("x",1.0f,0.3f);
        ValueAnimator scale=ValueAnimator.ofPropertyValuesHolder(x,y);
        scale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setScaleX((float) animation.getAnimatedValue("x"));
                iv.setScaleY((float) animation.getAnimatedValue("y"));
                iv.setAlpha((float) animation.getAnimatedValue("x"));
            }
        });
        scale.setDuration(2000);
        scale.setInterpolator(new BounceInterpolator());
        scale.setRepeatCount(3);
        scale.setRepeatMode(ValueAnimator.REVERSE);
        scale.setTarget(iv);
        scale.start();

    }

    private void move1() {
        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        ValueAnimator moveAnimator=ValueAnimator.ofInt(0,dm.widthPixels-iv.getWidth());
        moveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.MarginLayoutParams  layoutParams = (ViewGroup.MarginLayoutParams)iv.getLayoutParams();
                layoutParams.leftMargin=(int)animation.getAnimatedValue();
                iv.setLayoutParams(layoutParams);
            }
        });
        moveAnimator.setDuration(2000);
        moveAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        moveAnimator.setRepeatCount(4);
        moveAnimator.setRepeatMode(ValueAnimator.REVERSE);
        moveAnimator.setTarget(iv);
        moveAnimator.start();
    }
}
