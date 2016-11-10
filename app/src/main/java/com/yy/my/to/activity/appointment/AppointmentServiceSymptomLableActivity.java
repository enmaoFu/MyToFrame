package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceSympotmLableFlowLayoutAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;
import com.yy.my.to.views.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约服务选择症状的通用标签页面
 * @author enmaoFu
 * @date 2016年10月18日
 */
public class AppointmentServiceSymptomLableActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏返回
    private TextView center_text;//标题栏标题
    private TextView right_text;//标题栏提交
    private TextView tv_remind;

    private FlowLayout tcy_my_label, tcy_hot_label;
    private AppointmentServiceSympotmLableFlowLayoutAdapter mMyLabelAdapter, mHotLabelAdapter;
    private List<String> MyLabelLists, HotLabelLists;

    private static int TAG_REQUESTCODE = 0x101;

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_symptom_label);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        tv_remind = (TextView) findViewById(R.id.tv_remind);
        tcy_my_label = (FlowLayout) findViewById(R.id.tcy_my_label);
        tcy_hot_label = (FlowLayout) findViewById(R.id.tcy_hot_label);
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

        initSymptomLableData();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_back);
        center_text.setText("选择症状");
        right_text.setText("提交");
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
            case R.id.right_text:
                for(String myLable:MyLabelLists){
                    Log.v("print",myLable.toString());
                }
                break;
        }
    }

    /**
     * 初始化症状标签数据
     */
    public void initSymptomLableData(){

        String[] date = getResources().getStringArray(R.array.provinces);
        HotLabelLists = new ArrayList<>();
        for (int i = 0; i < date.length; i++) {
            HotLabelLists.add(date[i]);
        }
        mHotLabelAdapter = new AppointmentServiceSympotmLableFlowLayoutAdapter(this, HotLabelLists);
        tcy_hot_label.setAdapter(mHotLabelAdapter);
        tcy_hot_label.setItemClickListener(new TagCloudLayoutItemOnClick(1));

        MyLabelLists = new ArrayList<>();
        mMyLabelAdapter = new AppointmentServiceSympotmLableFlowLayoutAdapter(this, MyLabelLists);
        tcy_my_label.setAdapter(mMyLabelAdapter);
        tcy_my_label.setItemClickListener(new TagCloudLayoutItemOnClick(0));

        String labels = String.valueOf(getIntent().getStringExtra("labels"));
        if (!TextUtils.isEmpty(labels) && labels.length() > 0
                && !labels.equals("null")) {
            String[] temp = labels.split(",");
            for (int i = 0; i < temp.length; i++) {
                MyLabelLists.add(temp[i]);
            }
            ChangeMyLabels();
        }

    }

    /**
     * 刷新我的标签数据
     */
    private void ChangeMyLabels() {
        tv_remind.setVisibility(MyLabelLists.size() > 0 ? View.GONE
                : View.VISIBLE);
        tcy_my_label.setVisibility(MyLabelLists.size() > 0 ? View.VISIBLE
                : View.GONE);
        mMyLabelAdapter.notifyDataSetChanged();
    }

    /**
     * 标签的点击事件
     *
     * @author lijuan
     */
    class TagCloudLayoutItemOnClick implements FlowLayout.TagItemClickListener {
        int index;

        public TagCloudLayoutItemOnClick(int index) {
            this.index = index;
        }

        @Override
        public void itemClick(int position) {
            switch (index) {
                case 0:
                    MyLabelLists.remove(MyLabelLists.get(position));
                    ChangeMyLabels();
                    break;
                case 1:
                    if (MyLabelLists.size() < 5) {
                        if (HotLabelLists.get(position).equals("自定义")) {
                            startActivityForResult(
                                    new Intent(AppointmentServiceSymptomLableActivity.this,
                                            AppointmentServiceSymptomLableAddTagActivity.class),
                                    TAG_REQUESTCODE);
                        } else {
                            Boolean isExits = isExist(MyLabelLists,
                                    HotLabelLists.get(position));
                            if (isExits) {
                                showToast("此标签已经添加啦");
                                return;
                            }
                            MyLabelLists.add(HotLabelLists.get(position));
                            ChangeMyLabels();
                        }
                    } else {
                        showToast("最多只能添加5个标签");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 将数组里面的字符串遍历一遍，看是否存在相同标签
     *
     * @param str
     * @param compareStr
     * @return
     */
    public static Boolean isExist(List<String> str, String compareStr) {
        Boolean isExist = false;//默认沒有相同标签
        for (int i = 0; i < str.size(); i++) {
            if (compareStr.equals(str.get(i))) {
                isExist = true;
            }
        }
        return isExist;
    }

    /**
     * 回传数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (TAG_REQUESTCODE == requestCode) {
            if (resultCode == AppointmentServiceSymptomLableAddTagActivity.TAG_RESULTCODE) {
                String label = data.getStringExtra("tags");
                MyLabelLists.add(label);
                ChangeMyLabels();
            }
        }
    }

}
