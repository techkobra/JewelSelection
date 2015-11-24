package cn.wosoftware.jewelselection.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.wosoftware.jewelselection.R;

public class DesignerAdapter extends BaseAdapter {

	Context context;
	List<Map<String, Object>> data;

	public DesignerAdapter(Context context, List<Map<String, Object>> data) {
		super();
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	/** 用于 存放view */
	private class Holder {
		private TextView title;
		private ImageView figure;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.designer_list_item, null);
			/** 得到一个对象 用来存放 view，免得每次都需要findviewbyid */
			Holder holder = new Holder();
			holder.figure = (ImageView) convertView.findViewById(R.id.figure_title);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		}
		Holder holder = (Holder) convertView.getTag();
		holder.figure.setImageResource(R.drawable.ic_launcher);
		Map<String, Object> map = data.get(position);
		holder.title.setText((CharSequence) map.get("title"));
		/*** 给每个imageview加一个tag（用来在id相同时 进行区分） */
		return convertView;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
