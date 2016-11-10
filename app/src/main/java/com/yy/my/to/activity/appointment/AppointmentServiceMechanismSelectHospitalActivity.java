package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceMechanismSelectHospitalAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.entitys.appointment.AppointmentServiceMechanismSelectHospitalEntity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约服务医疗机构选择医院页面
 * @author enmaoFu
 * @date 2016年10月26日
 */

public class AppointmentServiceMechanismSelectHospitalActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标签
    private ImageView right_img;//标题栏搜索

    private Intent intent = new Intent();//意图

    private ListView mechanism_select_hospital_listview;//Listview
    private AppointmentServiceMechanismSelectHospitalAdapter appointmentServiceMechanismSelectHospitalAdapter;//选择医院适配器
    private List<AppointmentServiceMechanismSelectHospitalEntity> asmsheList = new ArrayList<>();//选择医院数据集

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_mechanism_select_hospital);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        mechanism_select_hospital_listview = (ListView)findViewById(R.id.mechanism_select_hospital_listview);
        appointmentServiceMechanismSelectHospitalAdapter = new
                AppointmentServiceMechanismSelectHospitalAdapter(AppointmentServiceMechanismSelectHospitalActivity.this);
        mechanism_select_hospital_listview.setAdapter(appointmentServiceMechanismSelectHospitalAdapter);
        mechanism_select_hospital_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(AppointmentServiceMechanismSelectHospitalActivity.this,AppointmentServiceMechanismHospitalDetailsActivity.class);
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
        initSelectHospitalListview();
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
        right_img.setVisibility(View.VISIBLE);
        center_text.setText("选择医院");
        left_img.setImageResource(R.mipmap.ic_back);
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
     * 初始化选择医院 listview 列表
     */
    public void initSelectHospitalListview(){

        AppointmentServiceMechanismSelectHospitalEntity appointmentServiceMechanismSelectHospitalEntity;

        for(int i = 0; i < 20; i++){

            appointmentServiceMechanismSelectHospitalEntity = new AppointmentServiceMechanismSelectHospitalEntity("重庆新桥医院",
                    "三甲","重庆市沙坪坝区新桥正街183号按时打算打算啊啊","200");

            asmsheList.add(appointmentServiceMechanismSelectHospitalEntity);

        }

        appointmentServiceMechanismSelectHospitalAdapter.setItems(asmsheList);

    }

}
