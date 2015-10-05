package com.example.quickindexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class QuickIndexBar extends View{
	
	/*private String[] index ={"A","B","C","D","E","F","G","H","I","J","K","L","M",
			"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};*/
	
	private String[] index;

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public QuickIndexBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setContent(String[] index){
		this.index = index;
	}
	
	private Paint paint;
	private int cellWidth, cellHeight;
	private void init(){
		paint = new Paint(); 
		
		//白色
		paint.setColor(Color.WHITE);
		//粗体
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		//字体大小
		paint.setTextSize(35);
		//抗锯齿
		paint.setAntiAlias(true);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		super.onDraw(canvas);
		if (cellWidth == 0) {
			cellWidth = getMeasuredWidth();
		}
		if (cellHeight == 0) {
			cellHeight = getMeasuredHeight()/index.length;
		}
		for (int i = 0; i < index.length; i++) {
			
			float x = cellWidth/2 - paint.measureText(index[i])/2;
			
			Rect bounds = new Rect();
			paint.getTextBounds(index[i], 0, index[i].length(), bounds);//bounds有了height
			
			float y = cellHeight/2 + bounds.height()/2 + i*cellHeight;
			
			paint.setColor(i == touchIndex? Color.GRAY : Color.WHITE);
			
			canvas.drawText(index[i], x, y, paint);
		}
	}
	
	private int touchIndex = -1;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int y = (int) event.getY();
		
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:

			if (touchIndex==y/cellHeight) {
				break;
			}
			touchIndex = y/cellHeight;
			if (touchIndex >= 0 && touchIndex < index.length) {
				if (listener!=null) {
					listener.ontouchIndex(index[touchIndex]);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			touchIndex = -1;
		default:
			break;
		}
		
		invalidate();
		return true;
	}
	
	private onTouchIndexListener listener;
	
	public onTouchIndexListener getListener() {
		return listener;
	}

	public void setListener(onTouchIndexListener listener) {
		this.listener = listener;
	}


	public interface onTouchIndexListener{
		void ontouchIndex(String word);
	}
}
