package com.yy.my.to.activity.personal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.activity.appointment.AppointmentServiceFillDateActivity;
import com.yy.my.to.activity.currency.CurrencySelectMyFamilyActivity;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

/**
 * 个人中心主页面
 * @author enmaoFu
 * @date 2016年10月12日
 */
public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener{

    private TextView center_text;//标题栏标题
    private ImageView left_img;//标题栏返回

    private Intent intent = new Intent();//意图

    private RelativeLayout person_setting_re;//设置
    private RelativeLayout person_my_family_re;//我的家人

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_personal);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        left_img = (ImageView)findViewById(R.id.left_img);
        person_setting_re = (RelativeLayout)findViewById(R.id.person_setting_re);
        person_my_family_re = (RelativeLayout)findViewById(R.id.person_my_family_re);
        left_img.setOnClickListener(this);
        person_setting_re.setOnClickListener(this);
        person_my_family_re.setOnClickListener(this);
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
        center_text.setText("个人中心");
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
            case R.id.person_setting_re:
                intent.setClass(PersonalCenterActivity.this,PersonSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.person_my_family_re:
                intent.setClass(PersonalCenterActivity.this,CurrencySelectMyFamilyActivity.class);
                startActivity(intent);
                break;
        }

    }

}
