package com.syncteam.sync;

import android.app.Activity;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import android.os.Bundle;
import android.widget.Toast;

public class YouTubeEmbed extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener {
	



	static private final String DEVELOPER_KEY = "add your own key here!";
	       static private final String VIDEO = "4SK0cUNMnMM";
	       @Override
	       protected void onCreate(Bundle savedInstanceState) {
	              super.onCreate(savedInstanceState);
	              setContentView(R.layout.activity_main);
	              YouTubePlayerView youTubeView = (YouTubePlayerView)
	            		 findViewById(R.id.youtube_view);
	           youTubeView.initialize(DEVELOPER_KEY, this);
	          }
	         @Override
	         public void onInitializationFailure(Provider provider,
	YouTubeInitializationResult error) {
	                Toast.makeText(this, "Oh no! "+error.toString(),
	Toast.LENGTH_LONG).show();
	       }
	       @Override
	       public void onInitializationSuccess(Provider provider, YouTubePlayer player,
	boolean wasRestored) {
	              player.loadVideo(VIDEO);
	       }
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
