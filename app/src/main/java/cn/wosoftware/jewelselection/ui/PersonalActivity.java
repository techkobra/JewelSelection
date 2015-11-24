package cn.wosoftware.jewelselection.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.wosoftware.jewelselection.R;

public class PersonalActivity extends Activity implements OnClickListener {
	ImageView back, setting;
	RelativeLayout personal_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		setting = (ImageView) findViewById(R.id.setting);
		personal_title = (RelativeLayout) findViewById(R.id.personal_title);
		back.setOnClickListener(this);
		setting.setOnClickListener(this);
		personal_title.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.setting:
			intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.personal_title:
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
