package com.yy.my.to.fragments.personal;

import android.view.View;
import android.widget.ListView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.personal.PersonMyFollowMechanismAdapter;
import com.yy.my.to.base.BaseFragment;
import com.yy.my.to.entitys.personal.PersonMyFollowMechanismEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心我的关注 关注的机构Fragment
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class PersonMyFollowMechanismFragment extends BaseFragment implements View.OnClickListener{

    private ListView person_my_follow_mechanism_list;//机构列表
    private PersonMyFollowMechanismAdapter personMyFollowMechanismAdapter;//适配器
    private List<PersonMyFollowMechanismEntity> personMyFollowMechanismEntityList = new ArrayList<>();//数据源

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_person_my_follow_mechanism;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        person_my_follow_mechanism_list = (ListView)findViewById(R.id.person_my_follow_mechanism_list);
        personMyFollowMechanismAdapter = new PersonMyFollowMechanismAdapter(getActivity());
        person_my_follow_mechanism_list.setAdapter(personMyFollowMechanismAdapter);
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

        PersonMyFollowMechanismEntity personMyFollowMechanismEntity = null;

        for(int i = 0; i < 20; i++){

            personMyFollowMechanismEntity = new PersonMyFollowMechanismEntity(R.mipmap.ls1,"北京协和医院","三甲","北京天安门");

            personMyFollowMechanismEntityList.add(personMyFollowMechanismEntity);

        }

        personMyFollowMechanismAdapter.setItems(personMyFollowMechanismEntityList);

    }

}
