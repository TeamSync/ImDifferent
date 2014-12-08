package com.syncteam.sync;

import java.util.Arrays;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.telephony.SmsManager;

public class ViewVideos extends Activity 
{
	
	
	public static String msgData = "";
	


	/**
	 * @param args
	 */
	TextView display;
    //static SMSReceiver2 message = new SMSReceiver2();
	//static SMSReceiver2 incomingMessage = new SMSReceiver2();
	//incomingMessage.message = "Bob";
	
	//This ^ actually pulls the text message string from the class SMSReceiver.java
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_videos);
		display = (TextView) findViewById(R.id.textView1);
		System.out.println("Hello, this is working.");
		
		//Downloaded from stackoverflow
		//Reads all (???) existing texts on phone and returns them in one long string 
		Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
		cursor.moveToFirst();

		do{
			for(int idx=0;idx<cursor.getColumnCount();idx++)
			{
				msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
			}
		}while(cursor.moveToNext());
		
		Log.d("ViewVideos: See original msgData string", msgData);
		displayVideoInvitations();		
	}
	
	public static String [] displayVideoInvitations ()
	{
	
		String[] words = new String [5];
		String URL = "";
		String Time = "";
		String[] arrayFinal = new String [2];
		//String timeMinusExclamationPoint;
		
		
		
		words = msgData.split(" ");
		Log.d("ViewVideos: See what 'words' looks like when split", "words looks like this: " + Arrays.toString(words));
		
		
		
		
		for (int i = 0; i < (words.length) - 10; i++)
		{
			if ((words[i].toString()).equals("body:Let's"))
			{
				if ((words[i + 1].toString()).equals ("watch") && words[i + 3].toString().equals("at") && words[i + 5].toString().equals("!")) // && isYouTubeLinkValidMethod(words[i+2])
				{
					URL = words[i + 2];
					Time = words[i + 4];
					/*timeMinusExclamationPoint = Time.substring(0, Time.length() -1);
					this piece of code would chop off the exclamation mark from the end of the time in the original message
					but we don't need this because the time and exclamation mark are separated by spaces*/
					arrayFinal[0]=URL;
					arrayFinal[1]=Time;
					Log.d("ViewVideos: See if arrayFinal is picking up corr. texts", "arrayFinal: " + Arrays.toString(arrayFinal));
					return arrayFinal; 
				}
			}
		}
		arrayFinal[0] = new String ("You have no current video invitations");
		Log.d("ViewVideos: See if arrayFinal is appropriate in case of no corr. texts", "arrayFinal: " + Arrays.toString(arrayFinal));
		return arrayFinal;
		
	}
	
	//This adds a back button
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if (item.getItemId() == android.R.id.home) {
		finish();	
		}
		return super.onOptionsItemSelected(item);
	}

}
