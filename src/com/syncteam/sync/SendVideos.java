package com.syncteam.sync;

import com.syncteam.sync.R;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendVideos extends Activity {

	Button button;
	EditText editPhoneNum;
	EditText editWatchTime;
	EditText editYouTubeLink;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_videos);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		button = (Button) findViewById(R.id.button);
		editPhoneNum = (EditText) findViewById(R.id.editPhoneNum);
		editWatchTime = (EditText) findViewById(R.id.editWatchTime);
		editYouTubeLink= (EditText) findViewById(R.id.editYouTubeLink);

		Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
		cursor.moveToFirst();

		String msgData = "";

		
		do{
			for(int idx=0;idx<cursor.getColumnCount();idx++)
			{
				msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
			}
		}while(cursor.moveToNext());

		Log.d("test", msgData);
		
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String phoneNo = editPhoneNum.getText().toString();
				String sms = "Let's watch " + editYouTubeLink.getText().toString() + " at " + editWatchTime.getText().toString() + "!";

				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();	
		}
		return super.onOptionsItemSelected(item);
	}
}
//Ellie was here! 11/26 at 5:25 PM







/*
 * import android.support.v7.app.ActionBarActivity; import android.os.Bundle;
 * import android.view.Menu; import android.view.MenuItem;
 * 
 * 
 * public class TextSender extends ActionBarActivity {
 * 
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.layoutdescription); }
 * 
 * 
 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
 * menu; this adds items to the action bar if it is present.
 * getMenuInflater().inflate(R.menu.text_sender, menu); return true; }
 * 
 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
 * action bar item clicks here. The action bar will // automatically handle
 * clicks on the Home/Up button, so long // as you specify a parent activity in
 * AndroidManifest.xml. int id = item.getItemId(); if (id ==
 * R.id.action_settings) { return true; } return
 * super.onOptionsItemSelected(item); } }
 */
