package com.example.compscipage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class NewsMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_menu, menu);
		return true;
	}
	
	public void newsClicked(View v){
	    if(v.getId() == R.id.newsButton){
	    	Intent newsIntent = new Intent(this, NewsActivity.class);
	    	startActivity(newsIntent);
	    }
	}
	public void eventsClicked(View v){
	    if(v.getId() == R.id.eventButton){
	    	Intent eventIntent = new Intent(this, EventsActivity.class);
	    	startActivity(eventIntent);
	    }
	}
	
	public void seminarsClicked(View v){
	    if(v.getId() == R.id.seminarButton){
	    	Intent seminarIntent = new Intent(this, SeminarsActivity.class);
	    	startActivity(seminarIntent);
	    }
	}
	

}


