package com.dongxiaoyu.mobilesafe;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anan.mobilesafe.R;

public class MainActivity extends Activity
{
	private TextView  text;
	private GridView gridview;
	private String[] names = {"返字","返字","返字","返字","返字","返字","返字","返字","返字"};
	private int[] resimage = {R.drawable.app,R.drawable.safe,R.drawable.settings,R.drawable.trojan,R.drawable.atools,
			R.drawable.callmsgsafe,R.drawable.netmanager,R.drawable.sysoptimize,R.drawable.ic_launcher};

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_view);
		text = (TextView) this.findViewById(R.id.m_text);
		text.setFocusable(true);
		text.setFocusableInTouchMode(true);
		
		gridview = (GridView) this.findViewById(R.id.m_gv);
		gridview.setAdapter(new HomeAdapter());
		
	}
	private class HomeAdapter extends BaseAdapter
	{

		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.gv_item, null);
			ImageView iv= (ImageView) item.findViewById(R.id.it_image);
			TextView tv = (TextView)item.findViewById(R.id.it_view);
			iv.setImageResource(resimage[position]);
			tv.setText(names[position]);
			return item;
		}
		
	}
	
}
