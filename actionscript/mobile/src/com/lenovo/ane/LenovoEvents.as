package com.lenovo.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class LenovoEvents 
	{ 
		public function LenovoEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 *init 
		 */		
		public static const RECT_SDK_STATUS:String = "LenovoInit";
		/**
		 * 用户登录
		 */
		public static const RECT_LOGIN_STATUS : String = "LenovoLogin";
		
		public static const RECT_SHOW_STATUS : String = "LenovoShowAccountPage";
		
		/**
		 * 用户注销
		 */
		public static const RECT_LOGOUT_STATUS : String = "LenovoExit";
		
		/**
		 * 充值
		 */
		public static const RECT_PAY_STATUS : String = "LenovoPay";
	} 
}