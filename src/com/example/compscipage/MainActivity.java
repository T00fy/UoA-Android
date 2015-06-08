package com.example.compscipage;


import java.io.InputStream;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

//Mostafa Alsari mals426 5571080
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DownloadImageTask d1 = new DownloadImageTask((ImageView)findViewById(R.id.imageView1));
		d1.onPostExecute(d1.doInBackground("http://redsox.tcs.auckland.ac.nz/CSS/CSService.svc/home_image"));
		
	}
	
	public void courseMenuClicked(View v){
	    if(v.getId() == R.id.courseButton){
	    	Intent courseIntent = new Intent(this, CourseMenuActivity.class);
	   	startActivity(courseIntent);
	    }
	}
	
	public void staffMenuClicked(View v){
	    if(v.getId() == R.id.staffButton){
	    	Intent staffIntent = new Intent(this, StaffMenuActivity.class);
	    	startActivity(staffIntent);
	    }
	}
	
	public void rssMenuClicked(View v){
	    if(v.getId() == R.id.rssButton){
	    	Intent newsIntent = new Intent(this, NewsMenuActivity.class);
	    	startActivity(newsIntent);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	  ImageView bmImage;

	  public DownloadImageTask(ImageView bmImage) {
	      this.bmImage = bmImage;
	  }

	  protected Bitmap doInBackground(String... urls) {
	      String urldisplay = urls[0];
	      Bitmap bMap = null;
	      try {
	        InputStream in = new java.net.URL(urldisplay).openStream();
	        bMap = BitmapFactory.decodeStream(in);
	      } catch (Exception e) {
	          Log.e("Error getting image", e.getMessage());
	          e.printStackTrace();
	      }
	      return bMap;
	  }

	  protected void onPostExecute(Bitmap result) {
	      bmImage.setImageBitmap(result);
	  }
	}


