package cn.wosoftware.jewelselection.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class MenuJsonParse {
	public static List<MenuItemBean> getListMenu(String jsonstr) throws Exception {
		List<MenuItemBean> mList = new ArrayList<MenuItemBean>();

		String menuTag = "";
		String menuName = "";

		JSONObject jsonObject = new JSONObject(jsonstr);
		JSONArray array = jsonObject.getJSONArray("menu");
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			menuTag = item.getString("name");// ��Ʒ����
			JSONArray array2 = item.getJSONArray("menu_items");// �����²˵�
			for (int j = 0; j < array2.length(); j++) {
				JSONObject item2 = array2.getJSONObject(j);
				menuName = item2.getString("name");// ����

				mList.add(new MenuItemBean(menuName, menuTag, i));
			}
		}
		Log.i("fuck_retutn", mList.toString());
		return mList;
	}
}
