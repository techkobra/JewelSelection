package cn.wosoftware.jewelselection.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.wosoftware.jewelselection.R;

public class SettingActivity extends Activity implements OnClickListener {
	ImageView back;
	TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
	RelativeLayout exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	public void initView() {
		back = (ImageView) findViewById(R.id.back);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);
		tv5 = (TextView) findViewById(R.id.tv5);
		tv6 = (TextView) findViewById(R.id.tv6);
		tv7 = (TextView) findViewById(R.id.tv7);
		tv8 = (TextView) findViewById(R.id.tv8);
		exit = (RelativeLayout) findViewById(R.id.exit);
		back.setOnClickListener(this);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		tv5.setOnClickListener(this);
		tv6.setOnClickListener(this);
		tv7.setOnClickListener(this);
		tv8.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.tv1:

			break;
		case R.id.tv2:

			break;
		case R.id.tv3:

			break;
		case R.id.tv4:

			break;
		case R.id.tv5:

			break;
		case R.id.tv6:

			break;
		case R.id.tv7:

			break;
		case R.id.exit:

			break;
		default:
			break;
		}
	}
}
