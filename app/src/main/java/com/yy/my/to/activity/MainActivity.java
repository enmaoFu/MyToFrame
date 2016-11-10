package com.yy.my.to.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.activity.appointment.AppointmenServiceMapActivity;
import com.yy.my.to.activity.appointment.AppointmentServiceActivity;
import com.yy.my.to.activity.personal.PersonalCenterActivity;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.Timer;
import java.util.TimerTask;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

/**
 * APP的主页面
 * @author enmaoFu
 * @date 2016年10月12日
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    //记录是否有首次按键
    private boolean mBackKeyPressed = false;

    //标题栏标题
    private TextView center_text;

    //标题栏更多
    private ImageView right_img;

    //预约服务
    private LinearLayout appointment_service_lin;

    //健康咨询
    private LinearLayout healthy_consultation_lin;

    //个人中心
    private LinearLayout personal_lin;

    //电子脉冲
    private PulsatorLayout pulsator;

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_main);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {

        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        appointment_service_lin = (LinearLayout)findViewById(R.id.appointment_service_lin);
        healthy_consultation_lin = (LinearLayout)findViewById(R.id.healthy_consultation_lin);
        personal_lin = (LinearLayout)findViewById(R.id.personal_lin);
        pulsator = (PulsatorLayout)findViewById(R.id.pulsator);
        pulsator.start();
        appointment_service_lin.setOnClickListener(this);
        healthy_consultation_lin.setOnClickListener(this);
        personal_lin.setOnClickListener(this);
        pulsator.setOnClickListener(this);

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        center_text.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        center_text.setText("时诊宝");
        right_img.setImageResource(R.mipmap.ic_main_title_right_img);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.appointment_service_lin:
                intent.setClass(MainActivity.this, AppointmentServiceActivity.class);
                startActivity(intent);
                break;

            case R.id.healthy_consultation_lin:
                showToast("功能正在建设中");
                break;

            case R.id.personal_lin:
                intent.setClass(MainActivity.this, PersonalCenterActivity.class);
                startActivity(intent);
                break;

        }

    }

    /**
     * 监听后退键，点击两次退出APP
     */
    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed){
            showToast("再按一次退出应用");
            mBackKeyPressed = true;
            //延时两秒，如果超出则擦除第一次按键记录
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        }else{
            //退出程序
            this.finish();
            System.exit(0);
        }
    }
}