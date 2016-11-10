package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceMechanismHospitalDetailsAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.entitys.appointment.AppointmentServiceMechanismHospailsDetailsEntity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约服务医疗机构医院详情页面
 * @author enmaoFu
 * @date 2016年10月27日
 */
public class AppointmentServiceMechanismHospitalDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题
    private ImageView right_img;//标题栏收藏医院

    private Intent intent = new Intent();//视图

    private ScrollView mechanism_doctor_scroll;//滑动

    private ListView mechanism_hospital_listview;//评价列表
    private List<AppointmentServiceMechanismHospailsDetailsEntity> asmhdeList = new ArrayList<>();//数据集
    private AppointmentServiceMechanismHospitalDetailsAdapter appointmentServiceMechanismHospailsDetailsAdapter;//适配器

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_mechanism_hospital_details);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        mechanism_doctor_scroll = (ScrollView)findViewById(R.id.mechanism_doctor_scroll);
        mechanism_hospital_listview = (ListView)findViewById(R.id.mechanism_hospital_listview);
        appointmentServiceMechanismHospailsDetailsAdapter = new
                AppointmentServiceMechanismHospitalDetailsAdapter(AppointmentServiceMechanismHospitalDetailsActivity.this);
        mechanism_hospital_listview.setAdapter(appointmentServiceMechanismHospailsDetailsAdapter);
        //设置进入页面不与嵌套的listview产生滑动冲突，从而在页面最顶端
        mechanism_doctor_scroll.post(
                new Runnable() {
                    public void run() {
                        //sv_container.fullScroll(ScrollView.FOCUS_UP);
                        mechanism_doctor_scroll.scrollTo(0, 0) ;
                    }
                });
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initMechanismHospitalDetailsData();
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
        right_img.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_back);
        center_text.setText("医院详情");
        right_img.setImageResource(R.mipmap.ic_appointment_service_doctor_details_collection_n);
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

    /**
     * 初始化医院详情评价数据
     */
    public void initMechanismHospitalDetailsData(){

        AppointmentServiceMechanismHospailsDetailsEntity appointmentServiceMechanismHospailsDetailsEntity;

        for(int i = 0; i < 20; i++){

            appointmentServiceMechanismHospailsDetailsEntity = new AppointmentServiceMechanismHospailsDetailsEntity("120*******9",
                    "这次上门检测也很好，医生的服务态度也非常不错，其实都是假的假的假的，哈哈哈哈撒打算打算德尔达四大四大四大按时打算打算打算打算打算打算的撒的撒","2016-09-22");

            asmhdeList.add(appointmentServiceMechanismHospailsDetailsEntity);

        }

        appointmentServiceMechanismHospailsDetailsAdapter.setItems(asmhdeList);

    }

}
