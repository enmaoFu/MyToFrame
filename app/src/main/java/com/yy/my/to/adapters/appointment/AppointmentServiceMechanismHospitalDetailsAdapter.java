package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceMechanismHospailsDetailsEntity;

/**
 * 预约服务医疗机构医院详情评价适配器
 * @author enmaoFu
 * @date 2016年10月24日
 */

public class AppointmentServiceMechanismHospitalDetailsAdapter extends BaseAdapter<AppointmentServiceMechanismHospailsDetailsEntity>{

    public AppointmentServiceMechanismHospitalDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_mechanism_hospital_details_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView mechanism_doctor_details_itme_phone = baseViewHolder.getView(R.id.mechanism_hospital_details_itme_phone);
        TextView mechanism_doctor_details_itme_text = baseViewHolder.getView(R.id.mechanism_hospital_details_itme_text);
        TextView mechanism_doctor_details_date = baseViewHolder.getView(R.id.mechanism_hospital_details_date);

        AppointmentServiceMechanismHospailsDetailsEntity appointmentServiceMechanismSelectHospitalEntity = itemList.get(position);

        mechanism_doctor_details_itme_phone.setText(appointmentServiceMechanismSelectHospitalEntity.getPhone());
        mechanism_doctor_details_itme_text.setText(appointmentServiceMechanismSelectHospitalEntity.getText());
        mechanism_doctor_details_date.setText(appointmentServiceMechanismSelectHospitalEntity.getDate());

        return convertView;
    }
}
