package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

/**
 * 预约服务添加修改问诊人通用页面（查看问诊人详情）
 * @author enmaoFu
 * @date 2016年10月17日
 */
public class AppointmentServiceAddPatientActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题
    private TextView right_text;//标题栏提交
    private Intent intent = new Intent();//视图

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_add_patient);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        left_img.setOnClickListener(this);
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
        right_text.setVisibility(View.VISIBLE);
        center_text.setText("预约服务");
        left_img.setImageResource(R.mipmap.ic_back);
        right_text.setText("提交");
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
        }
    }

}
