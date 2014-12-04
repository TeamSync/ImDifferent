package com.syncteam.sync;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver2 extends BroadcastReceiver {

	// Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        
        
        try 
        {
             
            if (bundle != null) 
            {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) 
                {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                     
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    System.out.println(senderNum);
                    System.out.println(message);
                         
                }
            	
                

              }
          } 
         catch (Exception e)
         { //don't worry about understanding this line or the next line
                Log.e("SmsReceiver", "Exception smsReceiver" +e);
         }
       
        
        //we added this code in our attempt to pass the textmessage to the "ScrollThroughTextMessages" class 
        
        /*
        byte[] pdusObj = new byte[0]; //obviously use the real pdu in your app
    	Intent intent = new Intent(this, ScrollThroughTextMessages.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	intent.putExtra("smsPdu", pdusObj);
        context.startActivity(intent);
        */
	}
	

	
	
	
}
