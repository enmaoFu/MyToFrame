package com.yy.my.to.activity.personal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseFragmentActivity;
import com.yy.my.to.fragments.personal.PersonOderStatusDocterFragment;
import com.yy.my.to.fragments.personal.PersonOderStatusMedicalInstitutionFragment;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

/**
 * 通用的预约订单状态主页面
 * @author enmaoFu
 * @date 2016年11月14日
 */
public class PersonOrderStatusPublicActivity extends BaseFragmentActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题

    private TextView person_order_status_doctor;//医生
    private TextView person_order_status_mechanism;//医疗机构

    private int notClickTextColor;//菜单字体颜色 未点击
    private int alreadyClickTextColor;//菜单字体颜色 已点击

    private FrameLayout content_frame;
    private PersonOderStatusDocterFragment posdFragment;//医生fragemnt
    private PersonOderStatusMedicalInstitutionFragment posmiFragment;//医疗机构fragment
    private FragmentManager fm;

    /**
     * 默认key
     */
    private static final String ALL_ORDERS = "all_orders";//全部订单
    private static final String HAS_BEEN_COMPLETED = "has_been_completed";//已完成订单
    private static final String HANG_IN_THE_AIR = "hang_in_the_air";//未完成订单
    private static final String TO_BE_EVALUATED = "to_be_evaluated";//待评价订单

    private String key;//从PersonalCenterActivity传递过来的值,根据值显示对应的标题

    /*
        此处应声明一个标识，用于接收从PersonalCenterActivity传递过来的值，判断是哪个按钮跳转进来的，以后获取数据的时候需要用到
        根据不同的按钮获取不同的数据
        private String getDataKey;
     */

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_person_order_status_public);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        content_frame = (FrameLayout)findViewById(R.id.content_frame);
        person_order_status_doctor = (TextView)findViewById(R.id.person_order_status_doctor);
        person_order_status_mechanism = (TextView)findViewById(R.id.person_order_status_mechanism);
        fm = getSupportFragmentManager();
        left_img.setOnClickListener(this);
        person_order_status_doctor.setOnClickListener(this);
        person_order_status_mechanism.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        key = bundle.getString("key");
        notClickTextColor = Color.parseColor("#515151");
        alreadyClickTextColor = Color.parseColor("#3FCD97");
        selectTab(0);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_back);
        /**
         * 根据不同按钮点击的跳转显示不同的标题
         */
        switch (key){
            case ALL_ORDERS:
                center_text.setText("全部");
                break;
            case HAS_BEEN_COMPLETED:
                center_text.setText("已完成");
                break;
            case HANG_IN_THE_AIR:
                center_text.setText("未完成");
                break;
            case TO_BE_EVALUATED:
                center_text.setText("待评价");
                break;
        }
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

            case R.id.person_order_status_doctor:
                selectTab(0);
                break;

            case R.id.person_order_status_mechanism:
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
                if(posdFragment == null){
                    posdFragment = new PersonOderStatusDocterFragment();
                    //Activity传递数据到指定的Fragment
                    /*asmwFragment.setArguments(bundle);
                    bundle.putString("poiName", poiName);
                    bundle.putString("address",address);*/
                    ft.replace(R.id.content_frame, posdFragment);
                }else{
                    ft.replace(R.id.content_frame, posdFragment);
                }

                person_order_status_doctor.setTextColor(alreadyClickTextColor);
                person_order_status_mechanism.setTextColor(notClickTextColor);

                break;

            case 1:
                if(posmiFragment == null){
                    posmiFragment = new PersonOderStatusMedicalInstitutionFragment();
                    ft.replace(R.id.content_frame, posmiFragment);
                }else{
                    ft.replace(R.id.content_frame, posmiFragment);
                }

                person_order_status_doctor.setTextColor(notClickTextColor);
                person_order_status_mechanism.setTextColor(alreadyClickTextColor);

                break;

        }

        ft.commit();

    }

}
