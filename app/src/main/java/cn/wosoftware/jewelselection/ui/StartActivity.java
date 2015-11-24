package cn.wosoftware.jewelselection.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import cn.wosoftware.jewelselection.R;

/**
 * 软件启动界面
 */
public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_my01);
		LinearLayout mLinear = (LinearLayout) findViewById(R.id.Fragment01Linear);
		mLinear.setBackgroundResource(R.drawable.start);
		// 加入动画
		ScaleAnimation an = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f, 180f,
				320f);
		an.setDuration(2000);
		mLinear.setAnimation(an);
		an.start();
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = hand.obtainMessage();
				hand.sendMessage(msg);
			}
		}.start();
	};

	Handler hand = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (isFristRun()) {
				Intent intent = new Intent(StartActivity.this,
						MainActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(StartActivity.this,
						FrameActivity.class);
				startActivity(intent);
			}
			finish();
		};
	};

	// 判断是否首次开启软件
	private boolean isFristRun() {
		SharedPreferences sharedPreferences = this.getSharedPreferences(
				"share", MODE_PRIVATE);
		boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		Editor editor = sharedPreferences.edit();
		if (!isFirstRun) {
			return false;
		} else {
			editor.putBoolean("isFirstRun", false);
			editor.commit();
			return true;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

		}

		return true;
	}

}
