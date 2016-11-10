package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceSelectDoctorAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.entitys.appointment.AppointmentServiceSelectDoctorEntity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约上门服务选择医生页面
 * @author enmaoFu
 * @date 2016年10月19日
 */

public class AppointmentServiceSelectDoctorActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题
    private ImageView right_img;//标题栏搜索

    private ListView appointment_service_select_doctor_listview;//医生listview
    private List<AppointmentServiceSelectDoctorEntity> appointmentServiceSelectDoctorEntityList = new ArrayList<>();//数据集
    private AppointmentServiceSelectDoctorAdapter appointmentServiceSelectDoctorAdapter;//适配器

    private Intent intent = new Intent();//视图

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_select_doctor);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        appointment_service_select_doctor_listview = (ListView)findViewById(R.id.appointment_service_select_doctor_listview);
        appointmentServiceSelectDoctorAdapter = new AppointmentServiceSelectDoctorAdapter(AppointmentServiceSelectDoctorActivity.this);
        appointment_service_select_doctor_listview.setAdapter(appointmentServiceSelectDoctorAdapter);
        appointment_service_select_doctor_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(AppointmentServiceSelectDoctorActivity.this,AppointmentServiceDoctorDetailsActivity.class);
                startActivity(intent);
            }
        });
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initListviewData();
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
        center_text.setText("选择医生");
        right_img.setImageResource(R.mipmap.ic_appointment_doctor_search);
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
     * 初始化医生listview
     */
    public void initListviewData(){

        AppointmentServiceSelectDoctorEntity appointmentServiceSelectDoctorEntity = null;

        for(int i = 0; i < 40; i++){

            appointmentServiceSelectDoctorEntity = new AppointmentServiceSelectDoctorEntity("你好世界","主治医师","重庆西南医院","88","300");

            appointmentServiceSelectDoctorEntityList.add(appointmentServiceSelectDoctorEntity);

        }

        appointmentServiceSelectDoctorAdapter.setItems(appointmentServiceSelectDoctorEntityList);

    }

}
