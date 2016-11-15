package com.yy.my.to.adapters.personal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.base.BaseAdapter;
import com.yy.my.to.base.BaseViewHolder;
import com.yy.my.to.entitys.personal.PersonMyFollowMechanismEntity;

/**
 * 个人中心我的关注机构适配器
 * @author enmaoFu
 * @date 2016年11月15日
 */

public class PersonMyFollowMechanismAdapter extends BaseAdapter<PersonMyFollowMechanismEntity>{

    public PersonMyFollowMechanismAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_person_my_follow_mechanism_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView person_my_follow_mechanism_img = baseViewHolder.getView(R.id.person_my_follow_mechanism_img);
        TextView person_my_follow_mechanism_name = baseViewHolder.getView(R.id.person_my_follow_mechanism_name);
        TextView person_my_follow_mechanism_title = baseViewHolder.getView(R.id.person_my_follow_mechanism_title);
        TextView person_my_follow_mechanism_address = baseViewHolder.getView(R.id.person_my_follow_mechanism_address);

        PersonMyFollowMechanismEntity personMyFollowMechanismEntity = itemList.get(position);

        person_my_follow_mechanism_img.setImageResource(R.mipmap.ls);
        person_my_follow_mechanism_name.setText(personMyFollowMechanismEntity.getPerson_my_follow_mechanism_name());
        person_my_follow_mechanism_title.setText(personMyFollowMechanismEntity.getPerson_my_follow_mechanism_title());
        person_my_follow_mechanism_address.setText(personMyFollowMechanismEntity.getPerson_my_follow_mechanism_address());

        return convertView;

    }
}
