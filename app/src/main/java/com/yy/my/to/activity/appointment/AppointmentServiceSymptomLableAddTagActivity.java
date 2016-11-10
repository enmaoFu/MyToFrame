package com.yy.my.to.activity.appointment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yy.my.to.R;

/**
 * 预约上门服务添加自定义标签
 * @author: enmaoFu
 * @date: 2016年10月18日
 */
public class AppointmentServiceSymptomLableAddTagActivity extends Activity implements View.OnClickListener{

    private EditText mEtLabel;
    private Button mBtnSure;
    public final static int TAG_RESULTCODE = 0x102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_service_stmptom_lable_tag);
        initView();
        initData();
    }

    private void initData() {
        // 根据输入框输入值的改变提示最大允许输入的个数
        mEtLabel.addTextChangedListener(new TextWatcher_Enum());
    }

    private void initView() {
        mEtLabel = (EditText) findViewById(R.id.et_label);
        mBtnSure = (Button) findViewById(R.id.btn_sure);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                String label = mEtLabel.getText().toString();
                if (TextUtils.isEmpty(label)) {
                    Toast.makeText(AppointmentServiceSymptomLableAddTagActivity.this,"自定义标签不应为空",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = getIntent();
                intent.putExtra("tags", label);
                setResult(TAG_RESULTCODE, intent);
                finish();
                break;
        }
    }

    /**
     * 根据输入框输入值的长度超过8个字符的时候，弹出输入的标签应控制在8个字
     *
     * @author lijuan
     *
     */
    class TextWatcher_Enum implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            int lenght = mEtLabel.getText().toString().trim().length();
            if (lenght > 8) {
                Toast.makeText(AppointmentServiceSymptomLableAddTagActivity.this,"输入的标签应控制在8个字",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
