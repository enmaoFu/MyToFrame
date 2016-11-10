package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceMechanismSelectHospitalEntity;

/**
 * 预约服务医疗机构选择医院适配器
 * @author enmaoFu
 * @date 2016年10月26日
 */

public class AppointmentServiceMechanismSelectHospitalAdapter extends BaseAdapter<AppointmentServiceMechanismSelectHospitalEntity>{

    public AppointmentServiceMechanismSelectHospitalAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_mechanism_select_hospital_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView mechanism_select_hospital_title = baseViewHolder.getView(R.id.mechanism_select_hospital_title);
        TextView mechanism_select_hospital_grade = baseViewHolder.getView(R.id.mechanism_select_hospital_grade);
        TextView mechanism_select_hospital_address = baseViewHolder.getView(R.id.mechanism_select_hospital_address);
        TextView mechanism_select_hospital_distance = baseViewHolder.getView(R.id.mechanism_select_hospital_distance);

        AppointmentServiceMechanismSelectHospitalEntity appointmentServiceMechanismSelectHospitalEntity = itemList.get(position);
        mechanism_select_hospital_title.setText(appointmentServiceMechanismSelectHospitalEntity.getHospitalTitle());
        mechanism_select_hospital_grade.setText(appointmentServiceMechanismSelectHospitalEntity.getHospitaGrade());
        mechanism_select_hospital_address.setText(appointmentServiceMechanismSelectHospitalEntity.getHospitalAddress());
        mechanism_select_hospital_distance.setText(appointmentServiceMechanismSelectHospitalEntity.getHospitalDistance() + "m");

        return convertView;
    }
}
