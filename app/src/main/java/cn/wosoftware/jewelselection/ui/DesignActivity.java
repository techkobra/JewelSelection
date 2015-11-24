package cn.wosoftware.jewelselection.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;
import cn.wosoftware.jewelselection.R;
import cn.wosoftware.jewelselection.adapter.MenuItemAdapter;
import cn.wosoftware.jewelselection.adapter.SideMenuAdapter;
import cn.wosoftware.jewelselection.utils.MenuItemBean;
import cn.wosoftware.jewelselection.utils.MenuJsonParse;

public class DesignActivity extends Activity implements OnClickListener, SectionIndexer {

	private InputStream inputStream;
	private String menuJsonStr;

	private final String url = "your url";
	private ListView menuListView;
	private ListView sideMenuListView;
	private MenuItemAdapter adapter;
	private SideMenuAdapter sideAdapter;

	private LinearLayout titleLayout;
	private TextView title;
	/**
	 * 上次第一个可见元素，用于滚动时记录标识。
	 */
	private int lastFirstVisibleItem = -1;
	private List<MenuItemBean> menuList;

	/**
	 * 上次选中的左侧菜单
	 */
	private View lastView;
	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design);
		inputStream = getResources().openRawResource(R.raw.menu);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		titleLayout = (LinearLayout) findViewById(R.id.title_layout);
		title = (TextView) this.findViewById(R.id.title_layout_catalog);

		menuListView = (ListView) findViewById(R.id.menu_lvmenu);
		// 右侧点击事件
		menuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((MenuItemBean) adapter.getItem(position)).getMenuName(),
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(DesignActivity.this, DesignDetailsActivity.class);
				startActivity(intent);
			}
		});

		sideMenuListView = (ListView) findViewById(R.id.side_menu_lv);
		// 左侧点击事件
		sideMenuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (lastView != null) {
					// 上次选中的view变回灰色
					lastView.setBackgroundColor(getResources().getColor(R.color.unfocused));
				}
				// 设置选中颜色为白色
				view.setBackgroundColor(getResources().getColor(R.color.white));
				TextView section_tv = (TextView) view.findViewById(R.id.section_tv);
				int location = adapter.getPositionForSection((Integer.parseInt(section_tv.getText().toString())));
				if (location != -1) {
					menuListView.setSelection(location);
				}
				lastView = view;
			}
		});
		menuJsonStr = getMenuString(inputStream);
		binddata(menuJsonStr);
	}

	private void binddata(String menuJsonStr) {
		try {
			menuList = MenuJsonParse.getListMenu(menuJsonStr);
			adapter = new MenuItemAdapter(getApplicationContext(), menuList);
			sideAdapter = new SideMenuAdapter(getApplicationContext(), menuList);
			menuListView.setAdapter(adapter);
			sideMenuListView.setAdapter(sideAdapter);
			menuListView.setOnScrollListener(mOnScrollListener);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 右侧菜品滚动
	 */
	private OnScrollListener mOnScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			int section = getSectionForPosition(firstVisibleItem);// 获取屏幕第一个item的section
			int nextSection = getSectionForPosition(firstVisibleItem + 1);
			int nextSecPosition = getPositionForSection(+nextSection);
			if (firstVisibleItem != lastFirstVisibleItem) {
				MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
				params.topMargin = 0;
				titleLayout.setLayoutParams(params);
				title.setText(menuList.get(getPositionForSection(section)).getMenuTag());
				if (lastView != null) {
					lastView.setBackgroundColor(getResources().getColor(R.color.unfocused));
				}
				lastView = sideMenuListView.getChildAt(section);
				lastView.setBackgroundColor(getResources().getColor(R.color.white));

			}
			if (nextSecPosition == firstVisibleItem + 1) {
				View childView = view.getChildAt(0);
				if (childView != null) {
					int titleHeight = titleLayout.getHeight();
					int bottom = childView.getBottom();
					MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
					if (bottom < titleHeight) {
						float pushedDistance = bottom - titleHeight;
						params.topMargin = (int) pushedDistance;
						titleLayout.setLayoutParams(params);
					} else {
						if (params.topMargin != 0) {
							params.topMargin = 0;
							titleLayout.setLayoutParams(params);
						}
					}
				}
			}
			lastFirstVisibleItem = firstVisibleItem;
		}
	};

	public int getSectionForPosition(int position) {
		return menuList.get(position).getMenuSection();
	};

	@Override
	public int getPositionForSection(int sectionIndex) {
		for (int i = 0; i < menuList.size(); i++) {
			int section = menuList.get(i).getMenuSection();
			if (section == sectionIndex) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static String getMenuString(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[409600];
		try {
			while ((len = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(outputStream.toByteArray());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
