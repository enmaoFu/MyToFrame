package com.yy.my.to.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;
import com.yy.my.to.utils.Verification;

/**
 * 登陆页面
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标签

    private EditText phone_input;//收手机号输入
    private TextView get_code;//获取验证码
    private EditText code_input;//验证码输入
    private TextView login_but;//登陆

    private String getPhone;//手机号
    private String getCode;//验证码

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        phone_input = (EditText)findViewById(R.id.phone_input);
        code_input = (EditText)findViewById(R.id.code_input);
        login_but = (TextView)findViewById(R.id.login_but);
        get_code = (TextView)findViewById(R.id.get_code);
        left_img.setOnClickListener(this);
        center_text.setOnClickListener(this);
        login_but.setOnClickListener(this);
        get_code.setOnClickListener(this);
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
        center_text.setText("登陆");
        left_img.setImageResource(R.mipmap.ic_back);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        getPhone = phone_input.getText().toString().trim();
        getCode = code_input.getText().toString().trim();

        switch (v.getId()){

            case R.id.left_img:
                backView();
                break;
            case R.id.get_code:
                if(getPhone.equals("")){
                    showToast("请输入手机号");
                    //phone_input.setError(Html.fromHtml("<font>请输入手机号</font>"));
                }else if(!Verification.isMobileNO(getPhone)){
                    showToast("请输入正确的手机号");
                    //phone_input.setError(Html.fromHtml("<font>请输入正确的手机号</font>"));
                }
                break;
            case R.id.login_but:
                if(getPhone.equals("")){
                    showToast("请输入手机号");
                    //phone_input.setError(Html.fromHtml("<font>请输入手机号</font>"));
                }else if(!Verification.isMobileNO(getPhone)){
                    showToast("请输入正确的手机号");
                    //phone_input.setError(Html.fromHtml("<font>请输入正确的手机号</font>"));
                }else if(getCode.equals("")){
                    showToast("请输入验证码");
                    //code_input.setError(Html.fromHtml("<font>请输入验证码</font>"));
                }else if(!getCode.equals("123456")){
                    showToast("请输入正确的验证码");
                    //code_input.setError(Html.fromHtml("<font>请输入正确的验证码</font>"));
                }else{
                    showToast("登陆成功。。。(好假的登陆成功啊)");
                }
                break;

        }

    }

}
