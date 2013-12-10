package com.lenovo.func;

import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.lenovo.mpay.ifmgr.IPayResultCallback;
import com.lenovo.mpay.ifmgr.SDKApi;
import com.lenovo.mpay.tools.PayRequest;

/**
 * 执行付费
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class LenovoPay implements FREFunction {

	private String TAG = "LenovoPay";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		int waresid;
		int price;
		String exorderno;
		String cpprivateinfo;
		String notifyurl;
		try
		{
			waresid = arg1[0].getAsInt();
			price = arg1[1].getAsInt();
			exorderno = arg1[2].getAsString();
			cpprivateinfo = arg1[3].getAsString();
			notifyurl = arg1[4].getAsString();
			
			callBack("waresid:"+waresid);
			callBack("price:"+price);
			callBack("exorderno:"+exorderno);
			callBack("cpprivateinfo:"+cpprivateinfo);
			callBack("notifyurl:"+notifyurl);
			
			startPay(waresid,price,exorderno,cpprivateinfo,notifyurl);
		}
		catch (Exception e) {
			// TODO: handle exception
			callBack("arg1v is error");
			return null;
		}
		callBack("success");
		//--------------------------------
		
		return result;
	}

	public void startPay(int waresid, int price,String exorderno,String cpprivateinfo,String notifyurl) {
		PayRequest payRequest = new PayRequest();
//		payRequest.addParam("notifyurl", notifyurl);
		payRequest.addParam("appid", LenovoConfig.PAY_APPID);
		payRequest.addParam("waresid", waresid);
		payRequest.addParam("exorderno", exorderno);
		payRequest.addParam("price", price);
		payRequest.addParam("cpprivateinfo", cpprivateinfo);
		
		String paramUrl = payRequest.genSignedUrlParamString(LenovoConfig.PAY_APPKEY);
		
		callBack("LenovoConfig.PAY_APPKEY:"+LenovoConfig.PAY_APPKEY);
		callBack("paramUrl:"+paramUrl);
		
		SDKApi.startPay(_context.getActivity(), paramUrl, new IPayResultCallback() {
			@Override
			public void onPayResult(int resultCode, String signValue,
					String resultInfo) {//resultInfo = 应用编号&商品编号&外部订单号
				if (SDKApi.PAY_SUCCESS == resultCode) {
					Log.e("xx", "signValue = " + signValue);
					if (null == signValue) {
						// 没有签名值，默认采用finish()，请根据需要修改
						Log.e("xx", "signValue is null ");
						Toast.makeText(_context.getActivity(), "没有签名值", Toast.LENGTH_SHORT)
								.show();
						// //finish();
					}
					boolean flag = PayRequest.isLegalSign(signValue,resultInfo,LenovoConfig.PAY_APPKEY);
					if (flag) {
						Log.e("payexample", "islegalsign: true");
						Toast.makeText(_context.getActivity(), "支付成功", Toast.LENGTH_SHORT)
								.show();
						// 合法签名值，支付成功，请添加支付成功后的业务逻辑
					} else {
						Toast.makeText(_context.getActivity(), "支付成功，但是验证签名失败",
								Toast.LENGTH_SHORT).show();
						// 非法签名值，默认采用finish()，请根据需要修改
					}
				} else if(SDKApi.PAY_CANCEL == resultCode){
					Toast.makeText(_context.getActivity(), "取消支付", Toast.LENGTH_SHORT)
					.show();
					// 取消支付处理，默认采用finish()，请根据需要修改
					Log.e("fang", "return cancel");
				}else {
					Toast.makeText(_context.getActivity(), "支付失败", Toast.LENGTH_SHORT)
							.show();
					// 计费失败处理，默认采用finish()，请根据需要修改
					Log.e("fang", "return Error");
				}

			}
		});
	}
	/**
	 * 结果传给AS端
	 */
	public void callBack(String status){
		Log.d(TAG, "-----status----"+status);
		_context.dispatchStatusEventAsync(TAG, status);
	}
}
