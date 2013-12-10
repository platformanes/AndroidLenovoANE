package com.lenovo.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.lenovo.lsf.account.PsAuthenServiceL;

/**
 * @author Rect
 * @version  Time：2013-12-6 
 */
@SuppressWarnings("deprecation")
public class LenovoShowAccountPage implements FREFunction {
	private String TAG = "LenovoShowAccountPage";
	private FREContext _context;
	
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		PsAuthenServiceL.showAccountPage(_context.getActivity(), LenovoConfig.RID);
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
