package com.yy.my.to.adapters.appointment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.appointment.AppointmentServiceMapPublicEntity;

/**
 * 预约 定位位置列表适配器
 * @author enmaoFu
 * @date 2016年11月3日
 */

public class AppointmentServiceMapPublicAdapter extends BaseAdapter<AppointmentServiceMapPublicEntity> {

    public AppointmentServiceMapPublicAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_appointment_service_map_public_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView title = baseViewHolder.getView(R.id.title);
        TextView address = baseViewHolder.getView(R.id.address);

        AppointmentServiceMapPublicEntity appointmentServiceMapPublicEntity = itemList.get(position);

        title.setText(appointmentServiceMapPublicEntity.getTitle());
        address.setText(appointmentServiceMapPublicEntity.getAddress());

        return convertView;
    }
}
