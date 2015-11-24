package cn.wosoftware.jewelselection.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.huewu.pla.lib.MultiColumnPullToRefreshListView;
import com.huewu.pla.lib.MultiColumnPullToRefreshListView.OnRefreshListener;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.wosoftware.jewelselection.R;
import cn.wosoftware.jewelselection.adapter.WaterfallAdapter;

public class FrameActivity extends FragmentActivity implements OnClickListener {
	public static int screenWidth;
	public static int screenHeigh;
	// 如果不想用下拉刷新这个特性，只是瀑布流，可以用这个：MultiColumnListView 一样的用法 pullimagelist
	private MultiColumnPullToRefreshListView waterfallView;
	private ViewPager guidePages;
	private ScheduledExecutorService scheduledExecutorService;
	private int currentItem;
	private ArrayList<View> viewList = new ArrayList<View>();
	private ImageView[] imageViews;
	private LinearLayout viewGroup;
	private View header;
	ImageView personal, cart;
	RelativeLayout identity, designer, former, retread, seek, spot;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			guidePages.setCurrentItem(currentItem);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
		initView();
		waterfallView.addHeaderView(header);
		guidePages.setOnPageChangeListener(new NavigationPageChangeListener());

		// 初始化图片加载库
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisc()
				// 图片存本地
				.cacheInMemory().displayer(new FadeInBitmapDisplayer(50)).bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY) // default
				.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.memoryCache(new UsingFreqLimitedMemoryCache(16 * 1024 * 1024))
				.defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);

		final ArrayList<String> imageList = new ArrayList<String>();
		// 添加item
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043531502.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043532264.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043533581.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043533571.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043534672.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043534854.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043535929.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043535784.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043536626.jpg");
		imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043536244.jpg");
		WaterfallAdapter adapter = new WaterfallAdapter(imageList, this);
		waterfallView.setAdapter(adapter);
		waterfallView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				// 下拉刷新要做的事
				imageList.clear();
				imageList.add("http://www.yjz9.com/uploadfile/2012/1219/20121219043536626.jpg");
				// 刷新完成后记得调用这个
				waterfallView.onRefreshComplete();
			}
		});
		fillGuanggao(imageList);
	}

	public void initView() {
		header = LayoutInflater.from(this).inflate(R.layout.viewpage_header, null);
		guidePages = (ViewPager) header.findViewById(R.id.guidePages);
		viewGroup = (LinearLayout) header.findViewById(R.id.viewGroup);
		waterfallView = (MultiColumnPullToRefreshListView) findViewById(R.id.pullimagelist);
		personal = (ImageView) findViewById(R.id.personal);
		cart = (ImageView) findViewById(R.id.cart);
		identity = (RelativeLayout) header.findViewById(R.id.identity);
		designer = (RelativeLayout) header.findViewById(R.id.designer);
		former = (RelativeLayout) header.findViewById(R.id.former);
		retread = (RelativeLayout) header.findViewById(R.id.retread);
		seek = (RelativeLayout) header.findViewById(R.id.seek);
		spot = (RelativeLayout) header.findViewById(R.id.spot);
		personal.setOnClickListener(this);
		identity.setOnClickListener(this);
		designer.setOnClickListener(this);
		former.setOnClickListener(this);
		retread.setOnClickListener(this);
		seek.setOnClickListener(this);
		spot.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void fillGuanggao(ArrayList<String> imageList) {
		for (int i = 0; i < imageList.size(); i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			iv.setBackgroundResource(R.drawable.ic_launcher);// 加载图片时的默认图
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewList.add(iv);
		}
		guidePages.setAdapter(new MyViewPagerAdapter(viewList));
		imageViews = new ImageView[imageList.size()];
		for (int i = 0; i < imageList.size(); i++) {
			ImageView imageView = new ImageView(this);
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 0, 5, 0);
			imageViews[i] = imageView;
			if (i == 0)
				imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_focused));
			else
				imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_unfocused));

			viewGroup.addView(imageViews[i]);
		}
	}

	// ==============
	class NavigationPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentItem = arg0;
			for (int i = 0; i < imageViews.length; i++) {
				if (arg0 == i) {
					imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_focused));
				} else {
					imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_unfocused));
				}
			}
		}

	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	// =============================
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		// 用一个定时器 来完成图片切换
		// Timer 与 ScheduledExecutorService 实现定时器的效果

		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 通过定时器 来完成 每2秒钟切换一个图片
		// 经过指定的时间后，执行所指定的任务
		// scheduleAtFixedRate(command, initialDelay, period, unit)
		// command 所要执行的任务
		// initialDelay 第一次启动时 延迟启动时间
		// period 每间隔多次时间来重新启动任务
		// unit 时间单位
		scheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(), 1, 5, TimeUnit.SECONDS);

		super.onStart();

	}

	// 用来完成图片切换的任务
	private class ViewPagerTask implements Runnable {

		public void run() {
			// 实现我们的操作
			// 改变当前页面
			currentItem = (currentItem + 1) % imageViews.length;
			// Handler来实现图片切换
			handler.obtainMessage().sendToTarget();
		}

	}

	@Override
	protected void onStop() {
		// 停止图片切换
		scheduledExecutorService.shutdown();

		super.onStop();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.personal:
			intent = new Intent(this, PersonalActivity.class);
			startActivity(intent);
			break;
		case R.id.designer:
			intent = new Intent(this, DesignActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}

}
