package com.example.quickindexbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.quickindexbar.QuickIndexBar.onTouchIndexListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private QuickIndexBar quickIndexBar;
	private ListView lv_main;
	private List<Frined> list;
	private TextView currentIndex;
	
	//https://github.com/Zhanglichun/android_fastindex.git
	
	private String[] index ={"A","B","C","D","E","F","G","H","I","J","K","L","M",
			"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		list = new ArrayList<Frined>();
		fillList();
		Collections.sort(list);
		
		currentIndex = (TextView) findViewById(R.id.currentIndex);
		
		lv_main = (ListView) findViewById(R.id.lv_main);
		lv_main.setAdapter(new MyAdapter());
		
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		
		quickIndexBar.setContent(index);
		
		quickIndexBar.setListener(new onTouchIndexListener() {
			
			@Override
			public void ontouchIndex(String word) {
				
				for (int i = 0; i < list.size(); i++) {
					String  first = pingYinUtil.getPingYin(list.get(i).getName()).charAt(0)+"";
					if (word.equals(first)) {
						
						showIndex(word);
						lv_main.setSelection(i);
						//只显示第一条
						break;
					}
				}
				
			}
		});
	}
	
	private Handler handler = new Handler();
	
	private void showIndex(String  word){
		currentIndex.setVisibility(View.VISIBLE);
		currentIndex.setText(word);
		
		handler.removeCallbacksAndMessages(null);
		
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				currentIndex.setVisibility(View.GONE);
			}
		}, 1500);
	}
	
	private void fillList(){
		
		list.add(new Frined("Anmi"));
		list.add(new Frined("Tom"));
		list.add(new Frined("Frank"));
		list.add(new Frined("曹立黎"));
		list.add(new Frined("何慧媛"));
		list.add(new Frined("廖羽雯"));
		list.add(new Frined("童丹"));
		list.add(new Frined("易小天"));
		list.add(new Frined("张洪文"));
		list.add(new Frined("张瑞君"));
		list.add(new Frined("李智博"));
		list.add(new Frined("李中山"));
		list.add(new Frined("李越泽"));
		list.add(new Frined("杨子文"));
		list.add(new Frined("杨庆"));
		list.add(new Frined("杨武晴"));
		list.add(new Frined("谢余毅"));
		list.add(new Frined("谢昕达"));
		list.add(new Frined("谢明亨"));
		list.add(new Frined("蒋钰浩"));
		list.add(new Frined("蒋振家"));
		list.add(new Frined("武博涵"));
		list.add(new Frined("路远航"));
		list.add(new Frined("岳震逻"));
		list.add(new Frined("宁骏杰"));
		list.add(new Frined("荣子然"));		
		list.add(new Frined("程向阳"));
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
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

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			Holder holder;
			if (arg1 == null) {
				arg1 = View.inflate(MainActivity.this, R.layout.item, null);
				holder = new Holder(arg1);
				arg1.setTag(holder);	
			}else{
				holder = (Holder) arg1.getTag();
			}
			
			Frined frined = list.get(arg0);
			String currentFirstWord = pingYinUtil.getPingYin(frined.getName()).charAt(0) + "";
			if (arg0 > 0) {
				String lastWord = pingYinUtil.getPingYin(list.get(arg0-1).getName()).charAt(0) + "";
				if (lastWord.equals(currentFirstWord)) {
					holder.index.setVisibility(View.GONE);
				}else {
					//与上一个索引不同  显示
					holder.index.setVisibility(View.VISIBLE);
					holder.index.setText(currentFirstWord);
				}
			}else{
				//position = 0 直接显示
				holder.index.setVisibility(View.VISIBLE);
				holder.index.setText(currentFirstWord);
			}
			holder.index.setText(currentFirstWord);
			holder.name.setText(frined.getName());
			return arg1;
		}
		class Holder{
			TextView name,index;
			public Holder(View arg1){
				index =  (TextView) arg1.findViewById(R.id.tv_index);
				name =  (TextView) arg1.findViewById(R.id.tv_item);
			}
		}	
	}
}
