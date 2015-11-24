package cn.wosoftware.jewelselection.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import cn.wosoftware.jewelselection.R;
import cn.wosoftware.jewelselection.adapter.DesignerAdapter;

public class DesignDetailsActivity extends Activity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design_details);
		lv = (ListView) findViewById(R.id.details_list);
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < data.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "title" + 1);
			data.add(map);
		}
		DesignerAdapter adapter = new DesignerAdapter(this, data);
		lv.setAdapter(adapter);
	}
}
