package com.lenovo.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.lenovo.func.LenovoExit;
import com.lenovo.func.LenovoInit;
import com.lenovo.func.LenovoLogin;
import com.lenovo.func.LenovoPay;
import com.lenovo.func.LenovoShowAccountPage;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class LenovoContext extends FREContext {
	/**
	 * INIT sdk
	 */
	private static final String LENOVO_FUNCTION_INIT = "lenovo_function_init";
	/**
	 * 登录Key
	 */
	private static final String LENOVO_FUNCTION_LOGIN = "lenovo_function_login";
	
	private static final String LENOVO_FUNCTION_SHOW = "lenovo_function_show";
	/**
	 * 付费Key
	 */
	private static final String LENOVO_FUNCTION_PAY = "lenovo_function_pay";
	/**
	 * 退出Key
	 */
	private static final String LENOVO_FUNCTION_EXIT = "lenovo_function_exit";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
//	       //映射
		   map.put(LENOVO_FUNCTION_INIT, new LenovoInit());
	       map.put(LENOVO_FUNCTION_LOGIN, new LenovoLogin());
	       map.put(LENOVO_FUNCTION_SHOW, new LenovoShowAccountPage());
	       map.put(LENOVO_FUNCTION_PAY, new LenovoPay());
	       map.put(LENOVO_FUNCTION_EXIT, new LenovoExit());
	       return map;
	}

}
