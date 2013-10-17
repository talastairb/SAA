package com.example.urlloader;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WVC extends WebViewClient {
	public boolean shouldOverrideUrlLoading(WebView v, String s){
		v.loadUrl(s);
		return true;
	}
	
}
