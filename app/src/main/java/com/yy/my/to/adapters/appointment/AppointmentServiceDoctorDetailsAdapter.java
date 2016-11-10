package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceDoctorDetailsEntity;

/**
 * 预约上门服务医生详情评价适配器
 * @author enmaoFu
 * @date 2016年10月24日
 */

public class AppointmentServiceDoctorDetailsAdapter extends BaseAdapter<AppointmentServiceDoctorDetailsEntity>{

    public AppointmentServiceDoctorDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_doctor_details_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView doctor_details_itme_phone = baseViewHolder.getView(R.id.doctor_details_itme_phone);
        TextView doctor_details_itme_text = baseViewHolder.getView(R.id.doctor_details_itme_text);
        TextView doctor_details_date = baseViewHolder.getView(R.id.doctor_details_date);

        AppointmentServiceDoctorDetailsEntity appointmentServiceDoctorDetailsEntity = itemList.get(position);

        doctor_details_itme_phone.setText(appointmentServiceDoctorDetailsEntity.getPhone());
        doctor_details_itme_text.setText(appointmentServiceDoctorDetailsEntity.getText());
        doctor_details_date.setText(appointmentServiceDoctorDetailsEntity.getDate());

        return convertView;
    }
}
