package cn.wosoftware.jewelselection.frg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import cn.wosoftware.jewelselection.R;
import cn.wosoftware.jewelselection.ui.FrameActivity;

@SuppressLint("ValidFragment")
public class MyFragmentthree extends Fragment {

	private Context ctx;

	public MyFragmentthree(Context ctx) {
		super();
		this.ctx = ctx;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		view = View.inflate(ctx, R.layout.fragment_my03, null);
		Button mBtn = (Button) view.findViewById(R.id.MyLoginBtn);
		mBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(ctx, FrameActivity.class);
				ctx.startActivity(intent);
			}
		});
		return view;
	}

}
