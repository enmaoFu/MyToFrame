package com.yy.my.to.activity.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

/**
 * 个人中心设置关于我们页面
 * @author enmaoFu
 * @date 2016年11月10日
 */
public class PersonSettingAboutUsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_person_about_us);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
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
        center_text.setText("关于我们");
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
        }
    }

}
