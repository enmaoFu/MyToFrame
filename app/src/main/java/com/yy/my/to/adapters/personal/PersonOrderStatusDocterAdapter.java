package com.yy.my.to.adapters.personal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.personal.PersonOrderStatusDocterEntity;

/**
 * 个人中心订单医生适配器
 * @author enmaoFu
 * @date 2016年11月14日
 */

public class PersonOrderStatusDocterAdapter extends BaseAdapter<PersonOrderStatusDocterEntity>{

    public PersonOrderStatusDocterAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_person_order_status_docter_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView person_order_img = baseViewHolder.getView(R.id.person_order_img);
        TextView person_order_name = baseViewHolder.getView(R.id.person_order_name);
        TextView person_order_title = baseViewHolder.getView(R.id.person_order_title);
        TextView person_order_title_address = baseViewHolder.getView(R.id.person_order_title_address);

        PersonOrderStatusDocterEntity personOrderStatusDocterEntity = itemList.get(position);

        person_order_img.setImageResource(R.mipmap.ls);
        person_order_name.setText(personOrderStatusDocterEntity.getPerson_order_name());
        person_order_title.setText(personOrderStatusDocterEntity.getPerson_order_title());
        person_order_title_address.setText(personOrderStatusDocterEntity.getPerson_order_title_address());

        return convertView;

    }
}
