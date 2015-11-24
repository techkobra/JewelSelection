package cn.wosoftware.jewelselection.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.wosoftware.jewelselection.R;

public class LoginActivity extends Activity implements OnClickListener {
	TextView register;
	ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 主线程，不要直接调用联网方法，要用异步或线程
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		// 获取View
		back = (ImageView) findViewById(R.id.back);
		register = (TextView) findViewById(R.id.register);
		register.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 点击事件
		Intent intent;
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.register:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
