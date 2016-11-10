package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceDoctorDetailsAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.entitys.appointment.AppointmentServiceDoctorDetailsEntity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约上门服务医生详情页面
 * @author enmaoFu
 * @date 2016年10月19日
 */
public class AppointmentServiceDoctorDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题
    private ImageView right_img;//标题栏收藏医生

    private ListView doctor_listview;//评论列表
    private AppointmentServiceDoctorDetailsAdapter appointmentServiceDoctorDetailsAdapter;//评论适配器
    private List<AppointmentServiceDoctorDetailsEntity> appointmentServiceDoctorDetailsEntityList = new ArrayList<>();//评论数据集

    private TextView doctor_details_appointment;//立即预约
    private ScrollView doctor_scroll;//滑动

    private Intent intent = new Intent();//视图

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_doctor_details);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        doctor_listview = (ListView)findViewById(R.id.doctor_listview);
        appointmentServiceDoctorDetailsAdapter = new AppointmentServiceDoctorDetailsAdapter(AppointmentServiceDoctorDetailsActivity.this);
        doctor_listview.setAdapter(appointmentServiceDoctorDetailsAdapter);
        doctor_details_appointment = (TextView)findViewById(R.id.doctor_details_appointment);
        doctor_scroll = (ScrollView)findViewById(R.id.doctor_scroll);
        //设置进入页面不与嵌套的listview产生滑动冲突，从而在页面最顶端
        doctor_scroll.post(
                new Runnable() {
                    public void run() {
                        //sv_container.fullScroll(ScrollView.FOCUS_UP);
                        doctor_scroll.scrollTo(0, 0) ;
                    }
                });
        left_img.setOnClickListener(this);
        doctor_details_appointment.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initDoctorDetailsCollectionData();
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
        center_text.setText("医生详情");
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
            case R.id.doctor_details_appointment:
                intent.setClass(AppointmentServiceDoctorDetailsActivity.this,AppointmentServiceConfirmAppointmentActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 初始化医生详情评论 listview 数据
     */
    public void initDoctorDetailsCollectionData(){

        AppointmentServiceDoctorDetailsEntity appointmentServiceDoctorDetailsEntity = null;

        for(int i = 0; i < 20; i++){
            appointmentServiceDoctorDetailsEntity = new AppointmentServiceDoctorDetailsEntity("120*******9",
                    "这次上门检测也很好，医生的服务态度也非常不错，其实都是假的假的假的，哈哈哈哈撒打算打算德尔达四大四大四大按时打算打算打算打算打算打算的撒的撒","2016-09-22");

            appointmentServiceDoctorDetailsEntityList.add(appointmentServiceDoctorDetailsEntity);

        }

        appointmentServiceDoctorDetailsAdapter.setItems(appointmentServiceDoctorDetailsEntityList);

    }

}
