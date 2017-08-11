package com.example.smile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class SmileView extends LinearLayout{
    private int defalutGravity = Gravity.CENTER_VERTICAL;

    public SmileView(Context context) {
        super(context);
        init(null, 0);
    }

    public SmileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SmileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private ImageView im;
    private AnimationDrawable animLike;
    private TextView text;
    private LinearLayout bg;
    private int defalutSize = dip2px(getContext(), 25);
    private void init(AttributeSet attrs, int defStyle) {
         this.removeAllViews();
         setOrientation(VERTICAL);
         setGravity(defalutGravity|Gravity.BOTTOM);
         setBackgroundColor(Color.TRANSPARENT);

        im=new ImageView(getContext());
        im.setBackgroundResource(R.drawable.animation_like);
        animLike=(AnimationDrawable)im.getBackground();

        text=new TextView(getContext());
        text.setText("好看");
        text.setTextSize(18f);
        text.setTextColor(Color.WHITE);

        bg=new LinearLayout(getContext());
        LayoutParams params2 = new LayoutParams(defalutSize,defalutSize);
        bg.addView(im,params2);
        bg.setBackgroundResource(R.drawable.white_background);


        LayoutParams params3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.setMargins(0, 10, 0, 0);
        params3.gravity = Gravity.BOTTOM;
        addView(text,params3);
        addView(bg,params3);
        //隐藏文字
        setVisibities(GONE);
        bindListen();
    }

    //
    public void setVisibities(int v) {
        text.setVisibility(v);
    }


    private void bindListen() {
        im.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundResource(R.drawable.color_background);
                animBack(); //拉伸背景
                setVisibities(VISIBLE); //隐藏文字

            }
        });
    }

    private ValueAnimator hightAn;
    private void animBack() {
        im.setClickable(false);
        hightAn=ValueAnimator.ofInt(0,120);
        hightAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LayoutParams l= (LayoutParams) im.getLayoutParams();
                l.bottomMargin=(int)animation.getAnimatedValue();
                im.setLayoutParams(l);
            }
        });
        hightAn.setDuration(2000);
        hightAn.start();
        hightAn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                topAnimaltion();
            }
        });
    }

    private void topAnimaltion() {
        animLike.start();
        ObjectAnimator paihuai=ObjectAnimator.ofFloat(im,"translationY",-10.0f,10.0f);
        paihuai.setDuration(500);
        paihuai.setRepeatMode(ObjectAnimator.REVERSE);
        paihuai.setRepeatCount(3);
        paihuai.start();
        paihuai.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                im.setTranslationY(0.0f);
                setBack();
            }
        });
    }

    private void setBack() {

        hightAn=ValueAnimator.ofInt(120,0);
        hightAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LayoutParams l= (LayoutParams) im.getLayoutParams();
                l.bottomMargin=(int)animation.getAnimatedValue();
                im.setLayoutParams(l);
            }
        });
        hightAn.setDuration(2000);
        hightAn.start();
        hightAn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                im.setClickable(true);
                setVisibities(GONE);
                bg.setBackgroundResource(R.drawable.white_background);
                //重置帧动画
                im.setBackgroundResource(R.drawable.like_1);
            }
        });
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
    //dp转px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
