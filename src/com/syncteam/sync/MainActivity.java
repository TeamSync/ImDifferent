package com.syncteam.sync;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void gotoActivitySendVideos(View v)
	{
		Intent intent =  new Intent(this, SendVideos.class);
		startActivity(intent);
	}
	public void gotoActivityViewVideos(View v)
	{
		Intent intent =  new Intent(this, ViewVideos.class);
		startActivity(intent);
	}
}


