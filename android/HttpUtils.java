package com.hacker.haohao.http.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



/**
 * 访问网络工具类,
 * @author ZhangHao
 * @version 1.0
 */
public class HttpUtils {

	/**
	 * 开启子线程并在其中进行耗时的网络连接操作；通过httpCallListener，返回网络处理结果（成功或异常）；
	 * 注意：监听中onFinish方法和onError方法式在子线程中开启的，所以不可以在这里进行Android UI的更新，可以
	 * 		 使用 异步的消息处理机制，或者使用Activity的runOnUiThread(Runable l) 方法。
	 * @param httpCallListener 监听
	 * @param address 网络连接地址
	 * @param 请求方式 GET
	 */
	public static void sendHttpRequestWithHttpURLConnection(final String address, final HttpCallListener httpCallListener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection httpURLConnection = null;
				try {
					URL url = new URL(address);
					httpURLConnection = (HttpURLConnection) url.openConnection();
					httpURLConnection.setConnectTimeout(8000);
					httpURLConnection.setRequestMethod("GET");
					httpURLConnection.setReadTimeout(8000);
					httpURLConnection.setDoInput(true);
					httpURLConnection.setDoOutput(true);
					InputStream inputStream = httpURLConnection.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream );
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					StringBuffer response = new StringBuffer();
					String line = null;
					while((line = bufferedReader.readLine()) != null){
						response.append(line);
					}
					if(null != httpCallListener){
						httpCallListener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if(null != httpCallListener){
						httpCallListener.onError(e);
					}
				}finally{
					//关闭链接
					if(null != httpURLConnection){
						httpURLConnection.disconnect();
					}
				}
			}
		}).start();
	}
	
	@SuppressWarnings("deprecation")
	public static void sendHttpRequestWithHttpClient(final String uri,final HttpCallListener httpCallListener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);
				try {
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						//请求响应都成功了
						 String response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
						 if(httpCallListener != null){
							 httpCallListener.onFinish(response);
						 }
					}else{
						if(httpCallListener != null){
							 httpCallListener.onError(new Exception("请求失败！！！"));
						 }
					}
				} catch (Exception e) {
					if(httpCallListener != null){
						 httpCallListener.onError(e);
					 }
				}
			}
		}).start();
	}
}
