package com.example.compscipage;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SeminarsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminars);
		new DoSeminarParseInBackground(this).execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seminars, menu);
		return true;
	}

}

class DoSeminarParseInBackground extends AsyncTask<Void, Void, ArrayList<News>>{
	private Context c1;
	
	public DoSeminarParseInBackground(Context context) {
		c1 = context;
	}
	
	@Override
	protected ArrayList<News> doInBackground(Void... arg0) {
    	NewsHandler newsHandler = new NewsHandler();
        try {
        	SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
		    XMLReader xr = sp.getXMLReader();
		    xr.setContentHandler(newsHandler);
		    xr.parse("http://www.cs.auckland.ac.nz/uoa/home/template/events_feed.rss?category=seminars");
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
        
		return newsHandler.getNewsList();
	}
	
	@Override
	protected void onPostExecute(final ArrayList<News> newsList) {
		super.onPostExecute(newsList);   
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayAdapter adapter = new ArrayAdapter(c1, android.R.layout.simple_list_item_2, android.R.id.text1, newsList) {
			  @Override
			  public View getView(int position, View convertView, ViewGroup parent) {
			    View view = super.getView(position, convertView, parent);
			    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
			    TextView text2 = (TextView) view.findViewById(android.R.id.text2);


			    text1.setText(Html.fromHtml("<a href="+newsList.get(position).getLink() +">" + newsList.get(position).getTitle()) );
			    text1.setMovementMethod(LinkMovementMethod.getInstance());
			    text2.setText(newsList.get(position).getFormattedString());
			    return view;
			  }
			};
			ListView lv = (ListView) ((Activity) c1).findViewById(R.id.seminarsSimpleList);
			lv.setAdapter(adapter);
		
		
		
	}

}