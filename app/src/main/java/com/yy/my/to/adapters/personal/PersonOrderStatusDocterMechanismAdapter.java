package com.yy.my.to.adapters.personal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.personal.PersonOrderStatusDocterMechanismEntity;

/**
 * 个人中心订单医疗机构适配器
 * @author enmaoFu
 * @date 2016年11月14日
 */

public class PersonOrderStatusDocterMechanismAdapter extends BaseAdapter<PersonOrderStatusDocterMechanismEntity>{

    public PersonOrderStatusDocterMechanismAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_person_order_status_mechanism_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView person_order_mechanism_img = baseViewHolder.getView(R.id.person_order_mechanism_img);
        TextView person_order_mechanism_name = baseViewHolder.getView(R.id.person_order_mechanism_name);
        TextView person_order_mechanism_title = baseViewHolder.getView(R.id.person_order_mechanism_title);
        TextView person_order_mechanism_title_address = baseViewHolder.getView(R.id.person_order_mechanism_title_address);

        PersonOrderStatusDocterMechanismEntity personOrderStatusDocterMechanismEntity= itemList.get(position);

        person_order_mechanism_img.setImageResource(R.mipmap.ls1);
        person_order_mechanism_name.setText(personOrderStatusDocterMechanismEntity.getPerson_order_mechanism_name());
        person_order_mechanism_title.setText(personOrderStatusDocterMechanismEntity.getPerson_order_mechanism_title());
        person_order_mechanism_title_address.setText(personOrderStatusDocterMechanismEntity.getPerson_order_mechanism_title_address());

        return convertView;

    }
}
