package com.wonders.core.commen;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	public static boolean isNetworkAvaliable(Context paramContext) {
	 NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
		if ((localNetworkInfo != null) && (localNetworkInfo.isConnected())) {
			return true;
		} else {
			return false;
		} 
	}
}
