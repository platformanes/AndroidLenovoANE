package com.lenovo.func;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.lenovo.lsf.account.PsAuthenServiceL;

/**
 * 执行登录
 * @author Rect
 * @version  Time：2013-5-8 
 */
@SuppressWarnings("deprecation")
public class LenovoLogin implements FREFunction {

	private String TAG = "LenovoLogin";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null;  
		// TODO Auto-generated method stub
		//--------------------------------
		
		login2(null, false, false, 0l);
		//--------------------------------
		
		return result;
	}

	private void login2(String username, boolean onekeyLogin,
			boolean handleErrorBySelf, Long times) {
		Bundle loginOption = new Bundle();
		final ProgressDialog progressDialog = new ProgressDialog(_context.getActivity());
		if (username != null) {
			loginOption.putString(PsAuthenServiceL.PRE_USERNAME, username);
		} else {
			loginOption.putBoolean(PsAuthenServiceL.PRE_AUTO_ONEKEY_LOGIN,
					onekeyLogin);
			loginOption.putLong(
					PsAuthenServiceL.PRE_AUTO_ONEKEY_LOGIN_TIME_OUT, times);
			loginOption.putBoolean(
					PsAuthenServiceL.PRE_AUTO_ONEKEY_LOGIN_HANDLE_BY_SELF,
					handleErrorBySelf);
			progressDialog.setMessage("正在登录,请稍后......");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
	    /**
	     * 功能：
                                   如果已登录，则从本地缓存获取ST并通过OnAuthenListener返回给应用。
                                   如果未登录，则弹出登录界面，供用户完成登录过程，并且把结果通过OnAuthenListener返回给应用。
                                   应用可以通过loginOption设置登录界面的数据，当前支持应用把用户名传递到登录界面。
                            输入参数：
             context[in]	当前Context，注意这里一定要传Acitivity 实例的context,否则在ICS以下版本的android系统上可能会出现类型转换错误；
             realm[in]		安全域
             cb[in]		完成请求后的返回值调用对象
             flag			true, 重新去服务器获取ST数据，false, 优先从本地缓存取有效ST数据
             loginOption  通过Bundle存入登录需要的预置数据，对应key值需要取PsAuthenServiceL中常量：
             1.	PRE_USERNAME     对应value为登录界面需要默认显示的用户名
             2.	PRE_AUTO_ONEKEY_LOGIN 值为true时执行自动一键登录，即不弹出登陆框，可以后台使用当前手机号直接登录。登录过程出现错误会弹出登录界面。
             3.	PRE_AUTO_ONEKEY_LOGIN_HANDLE_BY_SELF 此参数依赖于列表“2”参数，当“2”对应value为true时，如果执行自动一键登录过程中出错，那么会将错误信息直接返回到当前应用，不会弹出登录界面。
             4.	PRE_AUTO_ONEKEY_LOGIN_TIME_OUT，类型为long，一键登录超时时间（秒），不传或传入值小于等于0，则默认为90秒。
             5.	PRE_AUTHTOKEN对应value为一个特殊token，通过此token进行登录。
                        备注： PRE_AUTO_ONEKEY_LOGIN 只有是联想签名的应用才能使用，且一台设备一天内最多登录10次。 
                        返回值：
	                        空
	     */
		PsAuthenServiceL.getStData(_context.getActivity(), LenovoConfig.RID,
				new PsAuthenServiceL.OnAuthenListener() {

					@Override
					public void onFinished(boolean ret, String data) {
						Log.d("test", "hHHHHHHHHHHHHHHHHHh ret = " + ret
								+ ",  data = " + data);
						if (progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						if (ret) {
							callBack("login*0*"+data);
						} else {
							if (data != null && data.equals("cancel")) {
								callBack("loginCancel*2");
							} else {
								callBack(data);
								Toast.makeText(_context.getActivity(), data,
										Toast.LENGTH_LONG).show();
							}
						}

					}
				}, false, loginOption);

	}
	/**
	 * 结果传给AS端
	 */
	public void callBack(String status){
		Log.d(TAG, "login:"+status);
		_context.dispatchStatusEventAsync(TAG,status);
	}
}
