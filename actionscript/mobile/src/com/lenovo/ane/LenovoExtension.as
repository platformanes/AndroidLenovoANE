package com.lenovo.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class LenovoExtension extends EventDispatcher 
	{ 
		
		
		private static const LENOVO_FUNCTION_INIT:String = "lenovo_function_init";//与java端中Map里的key一致
		private static const LENOVO_FUNCTION_LOGIN:String = "lenovo_function_login";//与java端中Map里的key一致
		private static const LENOVO_FUNCTION_PAY:String = "lenovo_function_pay";//与java端中Map里的key一致
		private static const LENOVO_FUNCTION_EXIT:String = "lenovo_function_exit";//与java端中Map里的key一致
		private static const LENOVO_FUNCTION_SHOW:String = "lenovo_function_show";//与java端中Map里的key一致
		
		private static const EXTENSION_ID:String = "com.lenovo.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		/**单例的实例*/
		private static var _instance:LenovoExtension; 
		public function LenovoExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
			
		} 
		
		//第二个为参数，会传入java代码中的FREExtension的createContext方法
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():LenovoExtension
		{
			if(_instance == null) 
				_instance = new LenovoExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 * 
		 * @param kid
		 * @param book_registed_key
		 * @param book_registed_times
		 * @param next_book_registed_time
		 * @return 
		 * 
		 */			
		public function LenovoInit(
			kid:String,
			book_registed_key:String,
			book_registed_times:String,
			next_book_registed_time:String,
			storedPreference:int,
			appid:String,
			appkey:String):String{
			if(extContext ){
				return extContext.call(LENOVO_FUNCTION_INIT,
					kid,book_registed_key,book_registed_times,next_book_registed_time,
					storedPreference,appid,appkey
				) as String;
			}
			return "call LenovoInit failed"; 
		} 
		
		/**
		 *登录发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function LenovoLogIn(key:int):String{
			if(extContext ){
				return extContext.call(LENOVO_FUNCTION_LOGIN,key) as String;
			}
			return "call LenovoLogIn failed";
		} 
		
		public function LenovoShow(key:int):String{
			if(extContext ){
				return extContext.call(LENOVO_FUNCTION_SHOW,key) as String;
			}
			return "call LenovoShow failed";
		} 
		/**
		 * 
		 * @param waresid
		 * @param price
		 * @param exorderno
		 * @return 
		 * 
		 */			 
		public function LenovoPay(waresid:int,price:int,exorderno:String, 
								  cpprivateinfo:String,notifyurl:String):String{
			if(extContext){ 
				return extContext.call(LENOVO_FUNCTION_PAY,waresid,price,exorderno,
					cpprivateinfo,notifyurl)as String;
			}
			return "call LenovoPay failed";
		}
		
		/**
		 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
		 * @param key
		 * @return 
		 * 
		 */		
		public function LenovoExit(key:int):String{
			if(extContext){ 
				return extContext.call(LENOVO_FUNCTION_EXIT,key) as String;
			}
			return "call exit failed";
		}
	} 
}