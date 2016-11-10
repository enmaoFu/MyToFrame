package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

/**
 * 预约服务主页面
 * @author enmaoFu
 * @date 2016年10月12日
 */
public class AppointmentServiceActivity extends BaseActivity implements View.OnClickListener{

    private TextView center_text;//标题栏标题

    private ImageView left_img;//标题栏返回

    private ImageView appintment_home_img;//预约上门

    private ImageView appintment_mechanism_img;//预约医疗机构

    private Intent intent = new Intent();//视图

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        left_img = (ImageView)findViewById(R.id.left_img);
        appintment_home_img = (ImageView)findViewById(R.id.appintment_home_img);
        appintment_mechanism_img = (ImageView)findViewById(R.id.appintment_mechanism_img);
        left_img.setOnClickListener(this);
        appintment_home_img.setOnClickListener(this);
        appintment_mechanism_img.setOnClickListener(this);
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
        left_img.setVisibility(View.VISIBLE);
        center_text.setText("预约服务");
        left_img.setImageResource(R.mipmap.ic_back);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
            case R.id.appintment_home_img:
                intent.setClass(AppointmentServiceActivity.this,AppointmentServiceFillDateActivity.class);
                startActivity(intent);
                break;
            case R.id.appintment_mechanism_img:
                intent.setClass(AppointmentServiceActivity.this,AppointmentServiceMechanismFillDateActivity.class);
                startActivity(intent);
                break;
        }

    }

}
