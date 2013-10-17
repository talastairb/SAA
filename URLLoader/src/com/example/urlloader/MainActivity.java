package com.example.urlloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	Button go, save, load;
	EditText text;
	WebView browser;
	FileOutputStream fos;
	FileInputStream fis;
	String file;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		browser=(WebView)(findViewById(R.id.browser));
		browser.setWebViewClient(new WVC());
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		text = (EditText)(findViewById(R.id.text));
		go = (Button)(findViewById(R.id.go));
		go.setOnClickListener(this);
		save = (Button)(findViewById(R.id.save));
		save.setOnClickListener(this);
		load = (Button)(findViewById(R.id.load));
		load.setOnClickListener(this);
		file = "filename";
		try {
			fos = openFileOutput(file,Context.MODE_PRIVATE );
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fis = null;

	}
	public void onClick(View v){
		
		int id = v.getId();
		if(id == R.id.go){
			String site = text.getText().toString();
			Log.w("MainActivity", site);
			browser.loadUrl(site);
			
		}
		if(id == R.id.save){
			String site = text.getText().toString();
			try{
				fos = openFileOutput(file, Context.MODE_PRIVATE);
				fos.write(site.getBytes());
				fos.close();
				
			}catch(Exception e){}
			
		}
		if(id == R.id.load){
			String collected = "";
			try{
				fis = openFileInput(file);
				byte[]data = new byte[fis.available()];
				while(fis.read(data)!=-1){
					collected = new String(data);
					
				}
				
			}
			catch(Exception e){}
			finally{
				try{
					browser.loadUrl(collected);
					fis.close();
				}catch(Exception e){}
			}
		}
	}

	

}
