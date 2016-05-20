package com.example.fragmentactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private Button button1;
	private String fragmentName;
	private OnButtonClickedListener buttonClickedListener;
	/**
	 * 定义一个Handler用于接收黄色碎片给Activity发出来的指令
	 */
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg != null) {
				switch (msg.what) {
				case 100:
					/**
					 * 接收到黄色碎片发来的指令,Activity执行替换操作
					 */
					fragmentName = Fragment2.class.getName();
					replaceFragment(R.id.FrameLayout1, fragmentName);
					break;

				default:
					break;
				}
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		/**
		 * 加载黄色碎片
		 */
		fragmentName = Fragment1.class.getName();
		replaceFragment(R.id.FrameLayout1, fragmentName);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			if (buttonClickedListener != null) {
				buttonClickedListener
						.onclicked("Activity:我不想要这个黄色碎片了,点击下面的按钮给我换个绿色的碎片");
			}
			break;
		default:
			break;
		}

	}

	/*
	 * 
	 * 加载碎片
	 */
	protected void replaceFragment(int viewResource, String fragmentName) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment fragment = Fragment.instantiate(this, fragmentName);
		ft.replace(viewResource, fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
		getSupportFragmentManager().executePendingTransactions();
	}

	/**
	 * 定义一个接口
	 * 
	 * @author zqy
	 * 
	 */
	public interface OnButtonClickedListener {
		/**
		 * 里面传个值
		 * 
		 * @param s
		 */
		public void onclicked(String s);
	}

	/**
	 * 
	 * @param buttonClickedListener
	 *            写一个对外公开的方法
	 */
	public void setButtonClickedListener(
			OnButtonClickedListener buttonClickedListener) {
		this.buttonClickedListener = buttonClickedListener;
	}

}
