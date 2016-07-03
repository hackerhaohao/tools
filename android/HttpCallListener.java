package com.hacker.haohao.http.utils;


/**
 * HttpURLConnection 调用监听
 * @author ZhangHaoHao
 * Time 2016年7月3日11:39:09
 */
public interface HttpCallListener {

	/**
	 * 调用成功后回调方法
	 * @param response ： 返回的字符串
	 */
	public void onFinish(String response);

	/**
	 * 调用失败 后回调方法
	 * @param e ：  失败异常
	 */
	public void onError(Exception e);
}
