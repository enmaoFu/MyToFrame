package com.yy.my.to.adapters.personal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.personal.PersonMyFollowDocterEntity;

import static com.yy.my.to.R.id.person_order_img;
import static com.yy.my.to.R.id.person_order_name;
import static com.yy.my.to.R.id.person_order_title;
import static com.yy.my.to.R.id.person_order_title_address;

/**
 * 个人中心我的关注医生适配器
 * @author enmaoFu
 * @date 2016年11月15日
 */

public class PersonMyFollowDocterAdapter extends BaseAdapter<PersonMyFollowDocterEntity>{

    public PersonMyFollowDocterAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_person_my_follow_docter_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView person_my_follow_docter_img = baseViewHolder.getView(R.id.person_my_follow_docter_img);
        TextView person_my_follow_docter_name = baseViewHolder.getView(R.id.person_my_follow_docter_name);
        TextView person_my_follow_docter_title = baseViewHolder.getView(R.id.person_my_follow_docter_title);
        TextView person_my_follow_docter_address = baseViewHolder.getView(R.id.person_my_follow_docter_address);

        PersonMyFollowDocterEntity personMyFollowDocterEntity = itemList.get(position);

        person_my_follow_docter_img.setImageResource(R.mipmap.ls);
        person_my_follow_docter_name.setText(personMyFollowDocterEntity.getPerson_my_follow_docter_name());
        person_my_follow_docter_title.setText(personMyFollowDocterEntity.getPerson_my_follow_docter_title());
        person_my_follow_docter_address.setText(personMyFollowDocterEntity.getPerson_my_follow_docter_address());

        return convertView;

    }
}
