package com.yy.my.to.activity.personal;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseFragmentActivity;
import com.yy.my.to.fragments.personal.PersonMyFollowDocterFragment;
import com.yy.my.to.fragments.personal.PersonMyFollowMechanismFragment;
import com.yy.my.to.utils.ImmersedStatusbarUtils;


/**
 * 个人中心我的专注主页面
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class PersonMyFollowActivity extends BaseFragmentActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回

    private TextView person_my_follow_doctor_text;//关注的医生
    private TextView person_my_follow_mechanism_text;//关注的机构

    private FrameLayout content_frame;
    private PersonMyFollowDocterFragment pmfdFragment;//关注的医生fragment
    private PersonMyFollowMechanismFragment pmfmFragment;//关注的机构fragment
    private FragmentManager fm;

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_person_my_follow);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        person_my_follow_doctor_text = (TextView)findViewById(R.id.person_my_follow_doctor_text);
        person_my_follow_mechanism_text = (TextView)findViewById(R.id.person_my_follow_mechanism_text);
        content_frame = (FrameLayout)findViewById(R.id.content_frame);
        fm = getSupportFragmentManager();
        left_img.setOnClickListener(this);
        person_my_follow_doctor_text.setOnClickListener(this);
        person_my_follow_mechanism_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        selectTab(0);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        left_img.setImageResource(R.mipmap.ic_back);
        left_img.setVisibility(View.VISIBLE);
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
            case R.id.person_my_follow_doctor_text:
                selectTab(0);
                break;
            case R.id.person_my_follow_mechanism_text:
                selectTab(1);
                break;

        }
    }

    /*******************************************************************************************************************************/
    /**
     * Fragment菜单切换
     * @param postion 选择了第几个fragement
     */
    public void selectTab(int postion){

        FragmentTransaction ft = fm.beginTransaction();

        switch (postion) {
            case 0:
                if(pmfdFragment == null){
                    pmfdFragment = new PersonMyFollowDocterFragment();
                    ft.replace(R.id.content_frame, pmfdFragment);
                }else{
                    ft.replace(R.id.content_frame, pmfdFragment);
                }

                person_my_follow_doctor_text.setTextColor(Color.parseColor("#4CCAB4"));
                person_my_follow_doctor_text.setBackgroundResource(R.drawable.shape_person_my_follow_dorcter_style);
                person_my_follow_mechanism_text.setTextColor(Color.parseColor("#ffffff"));
                person_my_follow_mechanism_text.setBackgroundResource(R.drawable.shape_person_my_follow_mechanism_style1);

                break;

            case 1:
                if(pmfmFragment == null){
                    pmfmFragment = new PersonMyFollowMechanismFragment();
                    ft.replace(R.id.content_frame, pmfmFragment);
                }else{
                    ft.replace(R.id.content_frame, pmfmFragment);
                }

                person_my_follow_doctor_text.setTextColor(Color.parseColor("#ffffff"));
                person_my_follow_doctor_text.setBackgroundResource(R.drawable.shape_person_my_follow_dorcter_style1);
                person_my_follow_mechanism_text.setTextColor(Color.parseColor("#4CCAB4"));
                person_my_follow_mechanism_text.setBackgroundResource(R.drawable.shape_person_my_follow_mechanism_style);

                break;

        }

        ft.commit();

    }

}
