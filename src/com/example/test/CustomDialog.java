package com.example.test;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
/**
 * my dialog for many scene
 *
 * @author zhengfeilong
 * @date 2014-10-26
 */
public class CustomDialog extends Dialog implements OnClickListener{

	private static int default_width = LayoutParams.MATCH_PARENT;
	private static int default_height = LayoutParams.WRAP_CONTENT;
	private Context context = null;
	private View dialogContentView;
	
	/**
	 * wrap layout and center in screen;
	 * @param context
	 * @param layout
	 * @param style
	 */
	public CustomDialog(Context context, int layout, int style, int windowGravity) {
		super(context, style);
		
		View contentView  = LayoutInflater.from(context).inflate(layout, null);
		createDailog(context, contentView, style, windowGravity);
	}

	/**
	 * wrap layout and center in screen;
	 * @param context
	 * @param layout
	 * @param style
	 */
	public CustomDialog(Context context, View contentView, int style, int windowGravity) {
		super(context, style);
		
		createDailog(context, contentView, style, windowGravity);
	}
	
	private void createDailog(Context context, View contentView, int style, int windowGravity) {
		this.context = context;
		dialogContentView = contentView;
		
		FrameLayout.LayoutParams paramss = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
		setContentView(contentView, paramss);

		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.gravity = windowGravity;
		window.setAttributes(params);
	}

	/**
	 * （match screen width or have a little margin at left and right）and center in screen;
	 * @param context
	 * @param layout
	 * @param style
	 * @param isFullScreen
	 */
	public CustomDialog(Context context, int layout, int style, boolean isFullScreen, int windowGravity) {
		super(context, style);
		
		View contentView  = LayoutInflater.from(context).inflate(layout, null);
		createDailog(context, contentView, style, isFullScreen, windowGravity);
	}
	
	/**
	 * （match screen width or have a little margin at left and right）and center in screen;
	 * @param context
	 * @param layout
	 * @param style
	 * @param isFullScreen
	 */
	public CustomDialog(Context context, View contentView, int style, boolean isFullScreen, int windowGravity) {
		super(context, style);
		
		createDailog(context, contentView, style, isFullScreen, windowGravity);
	}
	

	private void createDailog(Context context2, View contentView, int style, boolean isFullScreen, int windowGravity) {
		this.context = context2;
		this.dialogContentView = contentView;
		
		setContentView(contentView);
		
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		float density = getDensity(context);
		
		// 代码修改，FILL_PARENT也会留出一个边
		int[] widthAndHeight = getSrceenPixels(context);
		
		if (isFullScreen) {
			params.width = (int) (widthAndHeight[0]);
		} else {
			params.width = (int) (widthAndHeight[0] - 20 * density);
		}

		params.height = default_height;
		params.gravity = windowGravity;
		window.setAttributes(params);
	}

	private float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}

	private int[] getSrceenPixels(Context context) {
		DisplayMetrics displaysMetrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		windowManager.getDefaultDisplay().getMetrics(displaysMetrics);
		int[] widthAndHeight = new int[2];
		widthAndHeight[0] = displaysMetrics.widthPixels;
		widthAndHeight[1] = displaysMetrics.heightPixels;
		return widthAndHeight;
	}

	/**
	 * regist clicklistener for view in dialog by id;
	 * @param ids
	 * @param onDialogViewClickListener
	 */
	public void setOnDialogViewClickListener(int ids[], OnDialogViewClickListener onDialogViewClickListener){
		this.onDialogViewClickListener = onDialogViewClickListener;
		for (int i = 0; i < ids.length; i++) {
			View view = dialogContentView.findViewById(ids[i]);
			if(view != null){
				view.setOnClickListener(this);	
			}
		}
	}

	
	@Override
	public void onClick(View v) {
		if (onDialogViewClickListener != null) {
			onDialogViewClickListener.dealDialogViewClick(v);
		}
	}
	
	private OnDialogViewClickListener onDialogViewClickListener;
	/**
	 * clicklistener for view in dialog
	 *
	 * @author zhengfeilong
	 * @date 2014-10-26
	 */
	public interface OnDialogViewClickListener{
		void dealDialogViewClick(View clickView);
	}
	
	
}
