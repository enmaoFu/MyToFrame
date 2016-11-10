package com.yy.my.to.dialogs;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yy.my.to.R;


/**
 * @description 自定义对话框 加载等待过渡动画
 * @author enmaoFu
 * @date 2016年09月13日
 */
public class WaitDialog extends ProgressDialog {

	private ImageView dialog_iv;
	private AnimationDrawable animationDrawable;
	private Context context;

	public WaitDialog(Context context) {
		super(context, R.style.waitDialog);
		setCanceledOnTouchOutside(false);
		this.context=context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initData() {
		dialog_iv.setBackgroundResource(R.drawable.dialog_animation);
		animationDrawable = (AnimationDrawable) dialog_iv.getBackground();
		animationDrawable.start();
	}

	public void setContent(String str) {
	}

	private void initView() {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.width = (int) (d.widthPixels * 0.3); // 高度设置为屏幕的0.6
		lp.height=(int) (d.widthPixels * 0.3);
		lp.alpha=0.5f;
		dialogWindow.setAttributes(lp);

		setContentView(R.layout.dialog_layout);
		dialog_iv = (ImageView) findViewById(R.id.dialog_iv);
	}
}
