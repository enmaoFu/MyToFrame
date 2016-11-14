package com.yy.my.to.fragments.personal;

import android.view.View;
import android.widget.ListView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.personal.PersonOrderStatusDocterAdapter;
import com.yy.my.to.base.BaseFragment;
import com.yy.my.to.entitys.personal.PersonOrderStatusDocterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的预约订单状态医生Fragment
 * @author enmaoFu
 * @date 2016年11月14日
 */
public class PersonOrderStatusDocterFragment extends BaseFragment implements View.OnClickListener{

    private ListView person_order_list;//医生列表
    private PersonOrderStatusDocterAdapter personOrderStatusDocterAdapte;//适配器
    private List<PersonOrderStatusDocterEntity> personOrderStatusDocterEntityList = new ArrayList<>();//数据源

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_person_order_status_docter;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        person_order_list = (ListView)findViewById(R.id.person_order_list);
        personOrderStatusDocterAdapte = new PersonOrderStatusDocterAdapter(getActivity());
        person_order_list.setAdapter(personOrderStatusDocterAdapte);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initListview();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {

    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化listview数据
     */
    public void initListview(){

        PersonOrderStatusDocterEntity personOrderStatusDocterEntity = null;

        for(int i = 0; i < 20; i++){

            personOrderStatusDocterEntity = new PersonOrderStatusDocterEntity(R.mipmap.ls,"张三丰","主治医师","重庆市沙坪坝区西南医院");

            personOrderStatusDocterEntityList.add(personOrderStatusDocterEntity);

        }

        personOrderStatusDocterAdapte.setItems(personOrderStatusDocterEntityList);

    }

}
