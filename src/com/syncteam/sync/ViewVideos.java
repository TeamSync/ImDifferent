package com.syncteam.sync;

import java.util.Arrays;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.telephony.SmsManager;


//Array of options --> ArrayAdapter --> ListView
//The listview only shows layouts, one after another
//List view: (views: da_items.xml)
//Create this layout.. 

public class ViewVideos extends Activity 
{
	
	
	public static String msgData = "";
	


	/**
	 * @param args
	 */
	TextView display;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		//display = (TextView) findViewById(R.id.textView1);
		System.out.println("Hello, this is working.");
		
		//Downloaded from stackoverflow
		//Reads all (???) existing texts on phone and returns them in one long string 
		Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
		cursor.moveToFirst();
		//int numberOfElements = 300;
		//cursor.getColumnCount()
		do{
			for(int idx=0;idx<cursor.getColumnCount();idx++)
			{
				msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
			}
		}while(cursor.moveToNext());
		
		Log.d("ViewVideos: See original msgData string", msgData);
		displayVideoInvitations();	//creates and returns String[] arrayFinal
		
		//String[] arrayFinal2 = displayVideoInvitations();
		
		//String[] arrayFinal = {"youtube.com, 5:00", "youtube.com, 4:00", "youtube.com, 3:00", "youtube.com, 2:00", "youtube.com, 1:00"};
		
		Log.d("ViewVideos", "The listView should have been created");
		
		populateListView(displayVideoInvitations()); //passes the newly created arrayFina
		
		
		
		
	}
	
	
	//In order to initialize an ArrayAdapter and ListView, we need 
	// a private void <methodname>(String[] arrayFinal) method
	
	private void populateListView(String[] arrayFinalParameter)
	{
		//Create list of items... not necessary we have an array "arrayFinal"
		// String[] myItems
		
		// Build Adapter
		Log.d("ViewVideos", "populateListView has begun");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
				(this,         //Context for this activity
				R.layout.a_invite,  //Layout to use (create)
				displayVideoInvitations()); //Items to be displayed //Would originally take in the strings.. 
		
		Log.d("ViewVideos", "Midway through populateListView");
		
		// Configure the list view... 
		ListView invites = (ListView) findViewById(R.id.finallist);
		invites.setAdapter(adapter);
		
		Log.d("ViewVideos", "populateListView has finished");
	}
	
	
	public static String [] displayVideoInvitations ()
	{
		int numberOfInvitations = 0;
		//String[] words = new String [5];
		String URL = "";
		String Time = "";
		String[] arrayFinal = new String [4];
		//String timeMinusExclamationPoint;
		

		
		String [] wordsByTime = (msgData.split("1417740695000"));
		String [] wordsBySpaceTime = (wordsByTime[0].toString().split(" "));
		Log.d("ViewVideos", "wordsBySpaceTime looks like this: " + Arrays.toString(wordsBySpaceTime));
		//Log.d("ViewVideos: See what 'words' looks like when split", "words looks like this: " + Arrays.toString(wordsBySpaceTime));
		
		for (int i = 0; i < (wordsBySpaceTime.length) - 10; i++)
		{
			Log.d("ViewVideos","i is: " + i);
			if ((wordsBySpaceTime[i].toString()).equals("body:Let's"))
			{
				if ((wordsBySpaceTime[i + 1].toString()).equals("watch") && wordsBySpaceTime[i + 3].toString().equals("at")) //&& wordsBySpaceTime[i + 5].toString().equals("!")) // && isYouTubeLinkValidMethod(words[i+2])
				{
					Log.d("ViewVideos","i is: " + i);
					Log.d("ViewVideos", "words[i] is " + wordsBySpaceTime[i]);
					URL = wordsBySpaceTime[i + 2];
					Time = wordsBySpaceTime[i + 4];
					/*timeMinusExclamationPoint = Time.substring(0, Time.length() -1);
					this piece of code would chop off the exclamation mark from the end of the time in the original message
					but we don't need this because the time and exclamation mark are separated by spaces*/
					//if (arrayFinal.length <= numberOfInvitations)
					//{
					//	arrayFinal = expandArray(arrayFinal);
					//}
					arrayFinal[numberOfInvitations]= URL;
					Log.d("ViewVideos", "arrayFinal looks like this inside the for loop: " + Arrays.toString(arrayFinal));
					numberOfInvitations ++;	
				}
			}	
		}
		if (/*arrayFinal[0] == null ||*/ arrayFinal.length == 0)
		{
			arrayFinal[0] = new String ("You have no current video invitations");
			Log.d("ViewVideos: See if arrayFinal is appropriate in case of no corr. texts", "arrayFinal: " + Arrays.deepToString(arrayFinal));
		}
		Log.d("ViewVideos: See if arrayFinal is picking up corr. texts", "arrayFinal: " + Arrays.deepToString(arrayFinal));
		return arrayFinal;
		
	}
	
	public static String [] expandArray(String[] originalArray) {
		String[] newArray = new String[originalArray.length*2];
		for(int i=0; i<originalArray.length; i++){
			newArray[i]=originalArray[i];
		}
		return newArray;
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
