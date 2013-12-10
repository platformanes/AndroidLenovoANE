package com.lenovo.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.lenovo.mpay.ifmgr.SDKApi;

/**
 * 初始化SDK
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class LenovoInit implements FREFunction {

	private String TAG = "LenovoInit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		//在这里做初始化的操作 我这里直接传回。。
		try
		{
			LenovoConfig.RID = arg1[0].getAsString();
			LenovoConfig.BOOT_REGISTED_KEY = arg1[1].getAsString();
			LenovoConfig.BOOT_REGISTED_TIMES = arg1[2].getAsString();
			LenovoConfig.NEXT_BOOT_REGISTED_TIME = arg1[3].getAsString();
			
			LenovoConfig.PAY_STORED_PREFRENCE = arg1[4].getAsInt();
			LenovoConfig.PAY_APPID = arg1[5].getAsString();
			LenovoConfig.PAY_APPKEY = arg1[6].getAsString();
			
			callBack("RID:"+LenovoConfig.RID);
			callBack("BOOT_REGISTED_KEY:"+LenovoConfig.BOOT_REGISTED_KEY);
			callBack("BOOT_REGISTED_TIMES:"+LenovoConfig.BOOT_REGISTED_TIMES);
			callBack("NEXT_BOOT_REGISTED_TIME"+LenovoConfig.NEXT_BOOT_REGISTED_TIME);
			callBack("PAY_STORED_PREFRENCE"+LenovoConfig.PAY_STORED_PREFRENCE);
			callBack("PAY_APPID"+LenovoConfig.PAY_APPID);
			callBack("PAY_APPKEY"+LenovoConfig.PAY_APPKEY);
			
			SDKApi.init(_context.getActivity(), LenovoConfig.PAY_STORED_PREFRENCE, LenovoConfig.PAY_APPID);
		}
		catch (Exception e) {
			// TODO: handle exception
			callBack("init argv is error");
			return null;
		}
		callBack("success");
		//--------------------------------
		
		return result;
	}

	/**
	 * 结果传给AS端
	 */
	public void callBack(String status){
		Log.d(TAG, "-----status----"+status);
		_context.dispatchStatusEventAsync(TAG, status);
	}

}
