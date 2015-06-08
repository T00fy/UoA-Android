package com.example.compscipage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.widget.ListView;
import android.widget.ProgressBar;

public class StaffMenuActivity extends Activity {
	public Context c1 = this;
	public static ProgressBar bar1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_staff_menu);
		bar1 = (ProgressBar) findViewById(R.id.progressBar2);
		new DoInBackground(this).execute();
			

	}
	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    super.onSaveInstanceState(savedInstanceState);
	    
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.staff_menu, menu);
		return true;
	}

}

class DoInBackground extends AsyncTask<Void, Void, ArrayList<VCard>> implements
		DialogInterface.OnCancelListener {
	private ArrayList<String> staffUPIs;
	private ArrayList<VCard> vCardList = new ArrayList<VCard>();
	private Context c1;
	
	public DoInBackground(Context context){
		c1 = context;
		staffUPIs = parseXmlOfStaffUpi();
		
	}
	
	protected void onPreExecute() {
	}
	
	private ArrayList<String> parseXmlOfStaffUpi() {
		StaffHandler staffHandler = new StaffHandler();
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(staffHandler);
			xr.parse("http://redsox.tcs.auckland.ac.nz/CSS/CSService.svc/people");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return staffHandler.getAllStaffUPI();
	}

	@Override
	protected void onPostExecute(ArrayList<VCard> vCardList) {
		super.onPostExecute(vCardList);
		
		StaffMenuActivity.bar1.setVisibility(ProgressBar.INVISIBLE);
		Activity staffMenuActivity = (Activity)c1;
		
		ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(staffMenuActivity, vCardList);
		ListView lv = (ListView) ((Activity) c1).findViewById(R.id.simplelist25);
		lv.setAdapter(adapter);
		
		
		
	}

	protected ArrayList<VCard> doInBackground(Void... wat) {
		
		for (int i = 0; i < staffUPIs.size(); i++) {
			String currentUPI = staffUPIs.get(i);
			VCard vcard = parseVCardOFStaffUPI(currentUPI);
			vCardList.add(vcard);
		}
		return vCardList;
	}

	private VCard parseVCardOFStaffUPI(String currentUPI) {
		try {
			
			URL url = new URL(
					"http://www.cs.auckland.ac.nz/our_staff/vcard.php?upi="
							+ currentUPI);
			BufferedReader in = new BufferedReader((new InputStreamReader(
					url.openStream())), 8192);
			/*
			 * Scanner s = new Scanner(in); String rawData = "";
			 * while(s.hasNext()){ rawData += s.nextLine() + "\n"; }
			 */

			HashMap<String, String[]> map = new HashMap<String, String[]>();
			String line = in.readLine();
			while (line != null) {
				String[] split = line.split(":", 2);
				map.put(split[0], split[1].split(";"));
				line = in.readLine();
			}

			String number = map.get("TEL;PREF;WORK;VOICE;ENCODING=QUOTED-PRINTABLE")[0];
			String name = map.get("FN")[0];
			String office = map.get("ADR;WORK;;ENCODING=QUOTED-PRINTABLE")[1];
			String email = map.get("EMAIL;INTERNET")[0];
			String pic = "";
			
			
			if(map.containsKey("PHOTO;TYPE=JPEG;ENCODING=BASE64")){
				pic = map.get("PHOTO;TYPE=JPEG;ENCODING=BASE64")[0];
			}else if(map.containsKey("PHOTO;TYPE=PNG;ENCODING=BASE64")){
				pic = map.get("PHOTO;TYPE=PNG;ENCODING=BASE64")[0];
			}
			VCard vcard = new VCard(name, office, email, pic, number);
			
			return vcard;
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("Something screwed up");
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		
	}



}
