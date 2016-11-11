package com.yy.my.to.activity.currency;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceChoicePatientAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.entitys.appointment.AppointmentServiceChoicePatientEntity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的选择问诊人(家人、问诊人)页面
 * @author enmaoFu
 * @date 2016年10月17日
 */
public class CurrencySelectMyFamilyActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回

    private TextView center_text;//标题栏标题

    private TextView right_text;//标题栏添加

    private Intent intent = new Intent();//视图

    private ListView appointment_service_fill_data_patient_listview;
    private AppointmentServiceChoicePatientAdapter appointmentServiceFillDataPatientAdapter;
    private List<AppointmentServiceChoicePatientEntity> appointmentServiceFillDataPatientEntityList = new ArrayList<>();

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_choice_patient);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        appointment_service_fill_data_patient_listview = (ListView)findViewById(R.id.appointment_service_fill_data_patient_listview);
        appointmentServiceFillDataPatientAdapter = new AppointmentServiceChoicePatientAdapter(CurrencySelectMyFamilyActivity.this);
        appointment_service_fill_data_patient_listview.setAdapter(appointmentServiceFillDataPatientAdapter);
        appointment_service_fill_data_patient_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(CurrencySelectMyFamilyActivity.this,CurrenyAddMyFamilyActivity.class);
                startActivity(intent);
            }
        });
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initListview();
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
        center_text.setText("我的家人");
        left_img.setImageResource(R.mipmap.ic_back);
        right_text.setText("添加");
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
            case R.id.right_text:
                intent.setClass(CurrencySelectMyFamilyActivity.this,CurrenyAddMyFamilyActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 初始化 listview 数据
     */
    public void initListview(){

        AppointmentServiceChoicePatientEntity appointmentServiceFillDataPatientEntity = null;

        for(int i = 0; i < 5; i++){

            appointmentServiceFillDataPatientEntity = new AppointmentServiceChoicePatientEntity("张三","女","20","500xxxxxxxxxx002","默认问诊人","185xxxx6856");

            appointmentServiceFillDataPatientEntityList.add(appointmentServiceFillDataPatientEntity);

        }

        appointmentServiceFillDataPatientAdapter.setItems(appointmentServiceFillDataPatientEntityList);

    }

}
