package com.yy.my.to.fragments.personal;

import android.view.View;
import android.widget.ListView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.personal.PersonMyFollowDocterAdapter;
import com.yy.my.to.base.BaseFragment;
import com.yy.my.to.entitys.personal.PersonMyFollowDocterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心我的关注 关注的医生Fragment
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class PersonMyFollowDocterFragment extends BaseFragment implements View.OnClickListener{

    private ListView person_my_follow_docter_list;//医生列表
    private PersonMyFollowDocterAdapter followDocterAdapter;//适配器
    private List<PersonMyFollowDocterEntity> personMyFollowDocterEntityList = new ArrayList<>();//数据集

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_person_my_follow_docter;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        person_my_follow_docter_list = (ListView)findViewById(R.id.person_my_follow_docter_list);
        followDocterAdapter = new PersonMyFollowDocterAdapter(getActivity());
        person_my_follow_docter_list.setAdapter(followDocterAdapter);
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
     * 初始化Listview数据
     */
    public void initListview(){

        PersonMyFollowDocterEntity personMyFollowDocterEntity = null;

        for(int i = 0; i < 20; i++){

            personMyFollowDocterEntity = new PersonMyFollowDocterEntity(R.mipmap.ls,"阿斯达","主治医师","重庆市沙坪坝区西南医院萨达");

            personMyFollowDocterEntityList.add(personMyFollowDocterEntity);

        }

        followDocterAdapter.setItems(personMyFollowDocterEntityList);

    }

}
