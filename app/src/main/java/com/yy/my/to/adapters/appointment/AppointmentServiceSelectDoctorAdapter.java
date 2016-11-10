package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceSelectDoctorEntity;

/**
 * 预约上门服务选择医生适配器
 * @author enmaoFu
 * @date 2016年10月19日
 */

public class AppointmentServiceSelectDoctorAdapter extends BaseAdapter<AppointmentServiceSelectDoctorEntity>{

    public AppointmentServiceSelectDoctorAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_select_doctor_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView doctor_name = baseViewHolder.getView(R.id.doctor_name);
        TextView doctor_title = baseViewHolder.getView(R.id.doctor_title);
        TextView doctor_company = baseViewHolder.getView(R.id.doctor_company);
        TextView doctor_price = baseViewHolder.getView(R.id.doctor_price);
        TextView doctor_distance = baseViewHolder.getView(R.id.doctor_distance);

        AppointmentServiceSelectDoctorEntity appointmentServiceSelectDoctorEntity = itemList.get(position);
        doctor_name.setText(appointmentServiceSelectDoctorEntity.getDoctorName());
        doctor_title.setText(appointmentServiceSelectDoctorEntity.getDoctorTitle());
        doctor_company.setText(appointmentServiceSelectDoctorEntity.getDoctorCompany());
        doctor_price.setText("¥ "+appointmentServiceSelectDoctorEntity.getDoctorPrice());
        doctor_distance.setText(appointmentServiceSelectDoctorEntity.getDoctorDistance()+"m");

        return convertView;
    }
}
