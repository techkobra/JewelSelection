package cn.wosoftware.jewelselection.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.wosoftware.jewelselection.frg.MyFragmentone;
import cn.wosoftware.jewelselection.frg.MyFragmentthree;
import cn.wosoftware.jewelselection.frg.MyFragmenttwo;

/**
 * Fragment适配器 FragmentPagerAdapter : 当我们的viewpager和fragment嵌套使用时实用的
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

	private Context ctx;

	// FragmentManager fragment管理器 ,上下文
	public ViewPagerAdapter(FragmentManager fm, Context ctx) {
		super(fm);
		this.ctx = ctx;
	}

	// 返回一个fragment
	// arg0 滑动到第几页
	@Override
	public Fragment getItem(int arg0) {
		Fragment mFragment = null;
		if (arg0 == 0) {
			mFragment = new MyFragmentone(ctx);
		} else if (arg0 == 1) {
			mFragment = new MyFragmenttwo(ctx);
		} else if (arg0 == 2) {
			mFragment = new MyFragmentthree(ctx);
		}
		return mFragment;
	}

	// 返回适配数量
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
