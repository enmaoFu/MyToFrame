package com.yy.my.to.fragments.personal;

import android.view.View;
import android.widget.ListView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.personal.PersonOrderStatusDocterMechanismAdapter;
import com.yy.my.to.base.BaseFragment;
import com.yy.my.to.entitys.personal.PersonOrderStatusDocterMechanismEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的预约订单状态医疗机构Fragment
 * @author enmaoFu
 * @date 2016年11月14日
 */
public class PersonOrderStatusMedicalInstitutionFragment extends BaseFragment implements View.OnClickListener{

    private ListView person_order_mechanism_list;//医生列表
    private PersonOrderStatusDocterMechanismAdapter personOrderStatusDocterMechanismAdapter;//适配器
    private List<PersonOrderStatusDocterMechanismEntity> personOrderStatusDocterMechanismEntityList = new ArrayList<>();//数据源

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_person_order_status_mechanism;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        person_order_mechanism_list = (ListView)findViewById(R.id.person_order_mechanism_list);
        personOrderStatusDocterMechanismAdapter = new PersonOrderStatusDocterMechanismAdapter(getActivity());
        person_order_mechanism_list.setAdapter(personOrderStatusDocterMechanismAdapter);
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

        PersonOrderStatusDocterMechanismEntity personOrderStatusDocterMechanismEntity = null;

        for(int i = 0; i < 20; i++){

            personOrderStatusDocterMechanismEntity = new PersonOrderStatusDocterMechanismEntity(R.mipmap.ls1,"重庆第三军医院","三甲","重庆市沙坪坝区1111111号");

            personOrderStatusDocterMechanismEntityList.add(personOrderStatusDocterMechanismEntity);

        }

        personOrderStatusDocterMechanismAdapter.setItems(personOrderStatusDocterMechanismEntityList);

    }

}
