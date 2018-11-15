package com.bwie.animatorsx;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageView;
    private Button alpha_bt;
    private Button rotationY_bt;
    private Button scaleX_bt;
    private Button translationX_bt;
    private Button AnimatorSet_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        init();

//     用xml的形式.引用在Xml里的属性动画资源.
        Animator Xmlanimator = AnimatorInflater.loadAnimator(this, R.animator.objectanimator);
//      把要做动画控件对象放进去.
        Xmlanimator.setTarget(imageView);
//      开启动画.
        Xmlanimator.start();

    }

    private void init() {

        imageView =  findViewById(R.id.animation_iv);
        alpha_bt =  findViewById(R.id.alpha_bt);
        rotationY_bt =  findViewById(R.id.rotationY_bt);
        scaleX_bt =  findViewById(R.id.scaleX_bt);
        translationX_bt =  findViewById(R.id.translationY_bt);
        AnimatorSet_bt =  findViewById(R.id.AnimatorSet_bt);
        //为button设置点击事件
        alpha_bt.setOnClickListener(this);
        rotationY_bt.setOnClickListener(this);
        scaleX_bt.setOnClickListener(this);
        translationX_bt.setOnClickListener(this);
        AnimatorSet_bt.setOnClickListener(this);

    }

    /**
     * 根据点击事件类型.调用控件做属性动画
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alpha_bt:
                //做渐变动画:
                // 参数1:View,代表你要修改那个控件的属性.
                // 参数2:propertyName代表实现什么样子的动画:"alpha",String类型.
                // 参数3:float... values,控件修改的参数
                ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha",
                        new float[]{0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f});
                //设置动画执行时长.
                alpha.setDuration(2000);
                //为动画设置监听
                alpha.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //开始动画
                        Toast.makeText(MainActivity.this, "动画开始执行", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //结束动画
                        Toast.makeText(MainActivity.this, "动画结束执行", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //取消动画

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        //重复动画
                    }
                });
                //设置动画执行的模式setRepeatMode
                // 参数用ObjectAnimator引用.
                alpha.setRepeatMode(ObjectAnimator.RESTART);
                //设置动画执行的次数.
                alpha.setRepeatCount(2);
                //使用ObjectAnimator对象开启动画.
                alpha.start();





                break;
            case R.id.rotationY_bt:
                //做翻转动画
                ObjectAnimator rotationY = ObjectAnimator.ofFloat(imageView, "rotationY",
                        new float[]{90f, 180f, 270f, 360f});
                rotationY.setDuration(2000);
                rotationY.setRepeatMode(ObjectAnimator.RESTART);
                rotationY.setRepeatCount(1);
                rotationY.start();
                break;
            case R.id.scaleX_bt:
                //做缩放动画
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX",
                        new float[]{1f, 2f, 3f, 4f, 5f, 6f, 1f});
                scaleX.setDuration(2000);
                scaleX.setRepeatMode(ObjectAnimator.RESTART);
                scaleX.setRepeatCount(1);
                scaleX.start();
                break;
            case R.id.translationY_bt:
                //做平移动画
                ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY",
                        new float[]{10f, 20f, 30f, 40f, 60f, 80f});
                translationY.setDuration(2000);
                translationY.setRepeatMode(ObjectAnimator.RESTART);
                translationY.setRepeatCount(1);
                translationY.start();


                // 做动画集合AnimatorSet
                // 分别创建两个动画对象.注意playTogether(一起执行)和playSequentially(顺序执行)，最后开启动画.start
            case R.id.AnimatorSet_bt:
                AnimatorSet set = new AnimatorSet();
                ObjectAnimator oa = ObjectAnimator.ofFloat(imageView, "translationX",
                        new float[]{10f, 20f, 30f, 40f, 60f, 80f});
                oa.setDuration(3000);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(imageView, "translationY",
                        new float[]{-10f, -20f, -30f, -40f, -60f, -80f});
                oa2.setDuration(3000);
                //set.playTogether(oa, oa2);//同时执行
                //set.setStartDelay(100);//延迟执行
                set.playSequentially(oa,oa2);//顺序执行

                set.start();
                break;
            default:
                break;
        }
    }

    /**
     * 根据点击事件,打印一段话
     */
    public void mxy(View v) {
        System.out.println("明天，你好");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
