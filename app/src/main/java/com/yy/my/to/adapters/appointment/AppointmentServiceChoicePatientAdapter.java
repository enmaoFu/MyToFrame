package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceChoicePatientEntity;

/**
 * 预约上门服务选择问诊人适配器
 * @author enmaoFu
 * @date 2016年10月17日
 */
public class AppointmentServiceChoicePatientAdapter extends BaseAdapter<AppointmentServiceChoicePatientEntity>{

    public AppointmentServiceChoicePatientAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_choice_patient_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView listview_itme_name = baseViewHolder.getView(R.id.listview_itme_name);
        TextView listview_itme_sex = baseViewHolder.getView(R.id.listview_itme_sex);
        TextView listview_itme_age = baseViewHolder.getView(R.id.listview_itme_age);
        TextView listview_itme_number = baseViewHolder.getView(R.id.listview_itme_number);
        TextView listview_itme_flag = baseViewHolder.getView(R.id.listview_itme_flag);
        TextView listview_itme_phone = baseViewHolder.getView(R.id.listview_itme_phone);

        AppointmentServiceChoicePatientEntity appointmentServiceFillDataPatientEntity = itemList.get(position);

        listview_itme_name.setText(appointmentServiceFillDataPatientEntity.getName());
        listview_itme_sex.setText(appointmentServiceFillDataPatientEntity.getSex());
        listview_itme_age.setText(appointmentServiceFillDataPatientEntity.getAge() + "岁");
        listview_itme_number.setText(appointmentServiceFillDataPatientEntity.getNumber());
        listview_itme_flag.setText(appointmentServiceFillDataPatientEntity.getFalg());
        listview_itme_phone.setText(appointmentServiceFillDataPatientEntity.getPhone());

        return convertView;
    }
}
