/**
 * 
 */
package com.syncteam.sync;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Jessica
 *this method is the one that should be able to populate the layout, using an array
 *we can create an Array within the .java file for the received videos button
 *whenever anything resembling the right format comes through, it accepts it
 */
public class ScrollThroughTextMessages {

	//I think this will also be the broad casting file..... creating the instances...
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        byte[] pdusObj = getIntent().getByteArrayExtra("smsPdu");
	        SmsMessage message = SmsMessage.createFromPdu(pdu);
	}
	
	private ListView lv;
	
	String test = message.SMSReceiver2; //this is taking place of the incoming SMS message
	
	public void onCreate(Bundle saveInstanecState){
		
		
		byte[] pdu = getIntent().getByteArrayExtra("smsPdu");
        SmsMessage message = SmsMessage.createFromPdu(pdu);
		setContentView(R.layout.layoutScrollThrough); //need to create this layout
		
		lv = (ListView)findViewById(R.id.your_list_view_id);
		
		//Instanciating an array list (you don't need to do this,
		//you already have your).
		
		//List<String> your_array_list = new ArrayList<String>();
		
		
		//this is where we for loop in the received SMS messages
		
		//while (SMS exists) //dont think I actually need a while loop, just a call to the receiver method
		//{
			//Boolean truth;
			//truth = SMStest(test);
			
			//if (truth = true)//Sms message is in the right format - might want to call a seperate method here
			//{
				ParsetheText (test);
				CheckIfRightFormat (parsedarray);
			//}
			
		//}
		
		//your_array_list.add("foo"); //what should really be here?
		//your_array_list.add("bar");
		
		//This is the array adapter, it takes the context of the activity as a
		//first parameter, the type of list view as a second
		//and the array as a third
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                words[] );
		
				lv.setAdapter(arrayAdapter);

				
		
		

	}
	public String[] ParsetheText (String textMessageInput)//is this method allowed to be here?
	{
		String URL = "";
		String Time = "";
		String[] words = new String[5];
		
		//int IndexOf = textMessageInput.indexOf(" ");
		//URL = textMessageInput.substring(0, IndexOf);
		//Time = textMessageInput.substring(IndexOf + 1, textMessageInput.length());
		
		//System.out.println(URL);
		//System.out.println(Time);
		
		words = textMessageInput.split(" ");
		
		
		//return (URL);
		//System.out.println(words[1]);
		//System.out.println(words[2]);
		//System.out.println(words[3]);
		//System.out.println(words[4]);
		
		URL = words[2];
		Time = words[4];
		String timeMinusExclamationPoint = Time.substring(0, Time.length() -1);
		//System.out.println("URL = " + URL);
		//System.out.println("Time = " + timeMinusExclamationPoint);
		CheckIfValidURL();
		return words;
		
	
	}
	
	
	
	/*
	 public Boolean SMStest (String message)//this is to test if it is a valid SMS message
	 {
		UrlValidator urlValidator = new UrlValidator(); 
		
		if (urlValidator.isValid(SMS message) = true) //instead of this need to use all of his code
			return true;
		else
			return false;
	}
	*/
}

