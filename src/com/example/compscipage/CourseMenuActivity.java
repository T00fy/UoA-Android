package com.example.compscipage;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CourseMenuActivity extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);  
        ArrayList<Course> courses = new ArrayList<Course>();
        courses = getXmlParse();
        
        ListView lv = (ListView) findViewById(R.id.simplelist26);

        
        ArrayAdapter<Course> arrayAdapter =      
        new ArrayAdapter<Course>(this,R.layout.plswork, courses);
        LayoutInflater inflater = this.getLayoutInflater();
        inflater.inflate(R.layout.activity_course_menu, null);
        lv.setAdapter(arrayAdapter); 
        
        
    }


    private ArrayList<Course> getXmlParse() {
		// TODO Auto-generated method stub
    	CourseHandler courseHandler = new CourseHandler();
        try {
        	SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
		    XMLReader xr = sp.getXMLReader();
		    xr.setContentHandler(courseHandler);
		    xr.parse("http://redsox.tcs.auckland.ac.nz/CSS/CSService.svc/courses");   
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courseHandler.getCourseList();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }
    
}
